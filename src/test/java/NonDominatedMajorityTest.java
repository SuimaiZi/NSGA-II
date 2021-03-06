import com.debacharya.nsgaii.Configuration;
import com.debacharya.nsgaii.NSGA2;
import com.debacharya.nsgaii.crossover.CrossoverParticipantCreatorProvider;
import com.debacharya.nsgaii.crossover.SimulatedBinaryCrossover;
import com.debacharya.nsgaii.mutation.PolynomialMutation;
import com.debacharya.nsgaii.objectivefunction.ObjectiveProvider;
import com.debacharya.nsgaii.plugin.GeneticCodeProducerProvider;
import com.debacharya.nsgaii.termination.NonDominatedMajority;

public class NonDominatedMajorityTest {

	public static void main(String[] args) {

		NonDominatedMajority ndm = new NonDominatedMajority();
		Configuration configuration = new Configuration(ObjectiveProvider.provideZDTObjectives());

		configuration.setGeneticCodeProducer(GeneticCodeProducerProvider.valueEncodedGeneticCodeProducer(0, 1, false));
		configuration.setCrossover(new SimulatedBinaryCrossover(
			CrossoverParticipantCreatorProvider.selectByBinaryTournamentSelection(),
			20
		));
		configuration.setMutation(new PolynomialMutation(0, 1));
		configuration.setTerminatingCriterion(ndm);
		configuration.setGenerations(200);
		configuration.setPopulationSize(100);
//		configuration.beSilent();

		NSGA2 nsga2 = new NSGA2(configuration);

		nsga2.run();
	}
}
