
package MBRSegSolution;

import static org.uma.jmetal.runner.AbstractAlgorithmRunner.printFinalSolutionSet;
import static org.uma.jmetal.runner.AbstractAlgorithmRunner.printQualityIndicators;

import java.io.IOException;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;

import Chart.Paint;
import Data.DataSetForSequoia;
import MBRSegProblem.ConstrainedSegProblem;
import util.Const;
import util.util;

public class ConstrainedSegSolution {
	public static void main(String[] args) throws IOException {
		Problem<IntegerSolution> problem;
		Algorithm<List<IntegerSolution>> algorithm;//
		CrossoverOperator<IntegerSolution> crossover;
		MutationOperator<IntegerSolution> mutation;
		SelectionOperator<List<IntegerSolution>, IntegerSolution> selection;
		String referenceParetoFront = "";
		DataSetForSequoia dataobjects = new DataSetForSequoia(Const.fileAdress);
		Paint.paintObjects(dataobjects);
		ConstrainedSegProblem ConSeg = new ConstrainedSegProblem(Const.numberOfSpatialObjects, dataobjects);
		problem = ConSeg;
		double crossoverProbability = 0.9;
		double crossoverDistributionIndex = 20.0;
		// crossover = new SBXCrossover(crossoverProbability,
		// crossoverDistributionIndex);
		crossover = new IntegerSBXCrossover(crossoverProbability, crossoverDistributionIndex);
		double mutationProbability = 1.0 / problem.getNumberOfVariables();
		// double mutationDistributionIndex = 20.0 ;
		mutation = new IntegerPolynomialMutation();
		selection = new BinaryTournamentSelection<IntegerSolution>(
				new RankingAndCrowdingDistanceComparator<IntegerSolution>());
		algorithm = new NSGAIIBuilder<IntegerSolution>(problem, crossover, mutation).setSelectionOperator(selection)
				.setMaxEvaluations(Const.MaxEvaluations).setPopulationSize(Const.PopulationSize).build();
		/*
		 * SequentialSolutionListEvaluator<DoubleSolution>(); algorithm = new
		 * NSGAII<DoubleSolution>(problem, 25000, 100, crossover, mutation,
		 * selection, evaluator);
		 */
		AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();
		List<IntegerSolution> population = algorithm.getResult();
		population=cleanSamePopulation(population);
		System.out.println(population.size());
		long computingTime = algorithmRunner.getComputingTime();
		ConSeg.paint();
		JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");
//		printFinalSolutionSet(population);
		util.printFinalSolutionSetToObjectives(population);
		Paint.paintResult(population);
		Paint.paintResult(population, dataobjects);
		if (!referenceParetoFront.equals(""))
			printQualityIndicators(population, referenceParetoFront);
	}
	
	private static List<IntegerSolution> cleanSamePopulation(List<IntegerSolution> population){
		for(int i=0;i<population.size()-1;i++)
		{
			for(int j=population.size()-1;j>i;j--)
			{
				if(population.get(j).equals(population.get(i))){
					population.remove(j);
				} 		
			}
		}
		
		return population;
	}
}
