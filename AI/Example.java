//This is my example Solution
import java.lang.Math;
import java.util.*; 

class Example {
	public static final int POP_SIZE = 100;
	public static final int SOL_SIZE = 20;
	public static final int POP_SIZE_2 = 100;
	public static final int SOL_SIZE_2 = 100;
	public static void main(String[] args)
	{
		//Do not delete/alter the next line
		long startT=System.currentTimeMillis();

		//Edit this according to your name and login
		String name="Sean Sanii Nejad";
		String login = "ss2439@kent.ac.uk";

		System.out.println("These are the instructions of how to use the problem library.  Please make sure you follow the instructions carefully.");
		System.out.println("For the first problem, you need to use Assess.getTest1(double[]).");
		
		//An example solution consists of an array  of doubles of size 20 
		//Allowed values are between -5 and +5 (this is not actually checked, but no point in going beyond these values)
		//Lower fitness is better. The optimal fitness is 0 (i.e. no negative fitness values). 
		double[] sol1 = generateCandidateSolution();		
		
		double[][] candidatePopulation = new double[POP_SIZE][SOL_SIZE];
		double[][] betterCandidatePopulation = new double[POP_SIZE][SOL_SIZE];	
		
		double[] firstCandidateSolution = new double[SOL_SIZE];
		double[] secondCandidateSolution = new double [SOL_SIZE];				
		double[] betterCandidateSolution = new double [SOL_SIZE];
				
		double[] fitness = new double[POP_SIZE];

		int generationCounter = 0;
								
		Random rand = new Random();						

	    	//Generate 100 candidate solutions			
	        for(int i=0; i<candidatePopulation.length;i++)
	        {
			candidatePopulation[i] = generateCandidateSolution(); 						
		}
			
		boolean check = true;
		while(check)
		{
			generationCounter = generationCounter + 1;
			for(int i=0; i<candidatePopulation.length; i++)
			{
				//Randomly Selected Candidate Solutions
				firstCandidateSolution = candidatePopulation[rand.nextInt(POP_SIZE)];			
				secondCandidateSolution = candidatePopulation[rand.nextInt(POP_SIZE)];									
				
				//Tournament 1			
				betterCandidatePopulation[i] = tournamentSelection(firstCandidateSolution, secondCandidateSolution);								
				
				//Mutating the tournament winner
				betterCandidatePopulation[i] = mutation(betterCandidatePopulation[i]);
				
				//Checking a candidate solution's fitness and cloning it to submission variable and exiting loop
				fitness[i] = Assess.getTest1(betterCandidatePopulation[i]);
				if(fitness[i] == 0)
				{
					sol1 = betterCandidatePopulation[i].clone();
					check = false;
				}
				//Outputting the fitness for each candidate solution and displaying the generation number
				System.out.println(generationCounter + " " + "Better Fitness solution: " + fitness[i]);				
			}
			//Assigning the better Candidate population to the original so the loop can repeat with the better population		
			candidatePopulation = betterCandidatePopulation;			
		}
									
//	----------------------------------------------------------------------------------------------------------------------------------------------------------------------------			
//		Second Problem:	
		System.out.println("Now let us turn to the second problem:");
		System.out.println("A sample solution in this case is a boolean array of size 100.");
		System.out.println("I now create a random sample solution and get the weight and utility:");

		//Creating a sample solution for the second problem
        	//The higher the fitness, the better, but be careful of  the weight constraint!
        	boolean[] sol2 = generateCandidateSolutionBoolean();
				
		boolean[][] candidatePopulationBoolean = new boolean[POP_SIZE][SOL_SIZE];
		boolean[][] betterCandidatePopulationBoolean = new boolean[POP_SIZE][SOL_SIZE];	
		
		boolean[] firstCandidateSolutionBoolean = new boolean[SOL_SIZE];
		boolean[] secondCandidateSolutionBoolean = new boolean [SOL_SIZE];
		boolean[] thirdCandidateSolutionBoolean = new boolean[SOL_SIZE];
		boolean[] forthCandidateSolutionBoolean = new boolean [SOL_SIZE];
				
		boolean[] betterCandidateSolutionBoolean = new boolean [SOL_SIZE];
		boolean[] mutatedCandidateSolutionBoolean = new boolean[SOL_SIZE];
		
		boolean[] finalCandidateSolutionBoolean = new boolean[SOL_SIZE];		
		double[] fitnessBoolean = new double[POP_SIZE];
	
		int crossoverChance2 = 80;
		
		//Generate 100 candidate solutions			
	        for(int i=0; i<candidatePopulationBoolean.length;i++)
	        {
	        	candidatePopulationBoolean[i] = generateCandidateSolutionBoolean();  					
			}	
			boolean check2 = true;
			while(check2)
			{
				long endT2= System.currentTimeMillis();
				generationCounter = generationCounter + 1;
				for(int i=0; i<candidatePopulationBoolean.length; i++)
				{
					//Randomly Selected Candidate Solutions
					firstCandidateSolutionBoolean = candidatePopulationBoolean[rand.nextInt(POP_SIZE_2)];			
					secondCandidateSolutionBoolean = candidatePopulationBoolean[rand.nextInt(POP_SIZE_2)];
					thirdCandidateSolutionBoolean = candidatePopulationBoolean[rand.nextInt(POP_SIZE_2)];			
					forthCandidateSolutionBoolean = candidatePopulationBoolean[rand.nextInt(POP_SIZE_2)];
					
					//Tournament 1		
					betterCandidateSolutionBoolean = tournamentSelectionBoolean(firstCandidateSolutionBoolean, secondCandidateSolutionBoolean);	
					//Tournament 2			
					betterCandidatePopulationBoolean[i] = tournamentSelectionBoolean(thirdCandidateSolutionBoolean, forthCandidateSolutionBoolean); 
									
					if(rand.nextInt(100) < crossoverChance2)
					{
						//Child of the two winning parents
						betterCandidatePopulationBoolean[i] = crossoverBoolean(betterCandidatePopulationBoolean[i], betterCandidateSolutionBoolean);
					}
					//Mutating the child
					betterCandidatePopulationBoolean[i] = mutationBoolean(betterCandidatePopulationBoolean[i]);
					fitnessBoolean = Assess.getTest2(betterCandidatePopulationBoolean[i]);	
					//Printing out the fitness of the mutated child
					System.out.println("fitness is: " + " " + Arrays.toString(fitnessBoolean));
	
					//Checking a candidate solution's fitness and cloning it to submission variable and exiting loop, solutions based on mark scheme.
								
					if(fitnessBoolean[0] <= 500 && fitnessBoolean[1] >= 210)
					{
						sol2 = betterCandidatePopulationBoolean[i].clone();
						check2 = false;					
					}
	
					if(fitnessBoolean[0] <= 500 && fitnessBoolean[1] >= 203 && (endT2 - startT)/1000.0 >= 4.75 && (endT2 - startT)/1000.0 <= 19.75)
					{
						sol2 = betterCandidatePopulationBoolean[i].clone();
						check2 = false;
					}
	
					if(fitnessBoolean[0] <= 500 && fitnessBoolean[1] >= 190 && (endT2 - startT)/1000.0 >= 20 && (endT2 - startT)/1000.0 <= 59.5)
					{
						sol2 = betterCandidatePopulationBoolean[i].clone();
						check2 = false;
					}
	            		}		
				candidatePopulationBoolean = betterCandidatePopulationBoolean;	                  
			}
	
			//Now checking the fitness of the candidate solution
			double[] tmp =(Assess.getTest2(sol2));
	
			//The index 0 of tmp gives the weight. Index 1 gives the utility
			System.out.println("The weight is: " + tmp[0]);
			System.out.println("The utility is: " + tmp[1]);
	
			//Once completed, your code must submit the results you generated, including your name and login: 
			//Use and adapt  the function below:
			Assess.checkIn(name,login,sol1,sol2);
	
			//Do not delete or alter the next line
			long endT= System.currentTimeMillis();
			System.out.println("Total execution time was: " +  ((endT - startT)/1000.0) + " seconds");
		}

	public static double[] crossover(double[] firstCandidateSolution, double[] secondCandidateSolution)
	{
		Random rand = new Random();
		int midpoint = rand.nextInt(SOL_SIZE);
		double[] crossoverCandidateSolution = new double[SOL_SIZE];
		for(int i=0; i<midpoint; i++)
		{			
			crossoverCandidateSolution[i] = firstCandidateSolution[i];
		}	

		for(int i=midpoint; i<crossoverCandidateSolution.length; i++)
		{			
			crossoverCandidateSolution[i] = secondCandidateSolution[i];
		}
		return crossoverCandidateSolution;
	}

	public static boolean[] crossoverBoolean(boolean[] firstCandidateSolutionBoolean, boolean[] secondCandidateSolutionBoolean)
	{
		Random rand = new Random();
		int midpoint = rand.nextInt(SOL_SIZE_2);
		boolean[] crossoverCandidateSolutionBoolean = new boolean[SOL_SIZE_2];
		for(int i=0; i<midpoint; i++)
		{			
			crossoverCandidateSolutionBoolean[i] = firstCandidateSolutionBoolean[i];
		}	

		for(int i=midpoint; i<crossoverCandidateSolutionBoolean.length; i++)
		{			
			crossoverCandidateSolutionBoolean[i] = secondCandidateSolutionBoolean[i];
		}	
		return crossoverCandidateSolutionBoolean;
	}

	public static double[] mutation(double[] betterCandidateSolution)
	{
		double[] mutatedCandidateSolution = betterCandidateSolution.clone();
		int mutationChance = 90;
		int chance = 50;
		int crazyChance = 1;
		Random rand = new Random();

		for(int i=0; i<mutatedCandidateSolution.length; i++)
		{
			if(rand.nextInt(100) < mutationChance)
			{
				double mutationChange = rand.nextDouble() * rand.nextDouble() * rand.nextDouble();
				double mutationChangePower = mutationChange * mutationChange;
				if(rand.nextInt(100) < chance)
				{			
					mutatedCandidateSolution[i] = betterCandidateSolution[i] + mutationChangePower;	
					if(Assess.getTest1(mutatedCandidateSolution) > Assess.getTest1(betterCandidateSolution))
					{
						mutatedCandidateSolution[i] = mutatedCandidateSolution[i] - mutationChangePower; 						
					}
				}
				else
				{					
					mutatedCandidateSolution[i] = betterCandidateSolution[i] - mutationChangePower;	
					if(Assess.getTest1(mutatedCandidateSolution) > Assess.getTest1(betterCandidateSolution))
					{
						mutatedCandidateSolution[i] = mutatedCandidateSolution[i] + mutationChangePower; 
					}	
				}				
			}
			else if(rand.nextInt(100) < crazyChance)
			{
				mutatedCandidateSolution[i] = generateCandidateSolution()[i];
			}				
		}		
		return mutatedCandidateSolution;
	}

	public static boolean[] mutationBoolean(boolean[] betterCandidateSolutionBoolean)
	{
		boolean[] mutatedCandidateSolutionBoolean = betterCandidateSolutionBoolean.clone();
		int mutationChance = 1;
		int chance = 50;
		Random rand = new Random();

		for(int i=0; i<mutatedCandidateSolutionBoolean.length; i++)
		{
			if(rand.nextInt(100) < mutationChance)
			{			
				if(rand.nextInt(100) < chance)
				{
					mutatedCandidateSolutionBoolean[i] = false;	
				}
				if(rand.nextInt(100) < chance)
				{
					mutatedCandidateSolutionBoolean[i] = true;	
				}
			}
		}
		return mutatedCandidateSolutionBoolean;
	}

	public static double[] tournamentSelection(double[] firstCandidateSolution, double[] secondCandidateSolution)
	{															
		if(Assess.getTest1(firstCandidateSolution) < Assess.getTest1(secondCandidateSolution))
		{
			return firstCandidateSolution;
		}
		else
		{
			return secondCandidateSolution;
		}					
	}

	public static boolean[] tournamentSelectionBoolean(boolean[] firstCandidateSolutionBoolean, boolean[] secondCandidateSolutionBoolean)
	{													
		if(fitnessCalculator(firstCandidateSolutionBoolean) > fitnessCalculator(secondCandidateSolutionBoolean))
		{
			return firstCandidateSolutionBoolean;
		}
		else
		{
			return secondCandidateSolutionBoolean;
		}					
	}

	public static double[] generateCandidateSolution()
	{
		//generate a sample solution like so:		
		double[] sol1= new double[SOL_SIZE];		
		for(int j=0; j<SOL_SIZE;j++)
		{
			sol1[j] = Math.random()*Math.round(5.12*(Math.random() - Math.random()));																									
		}
		return sol1;
	}
	
	public static boolean[] generateCandidateSolutionBoolean()
	{
		//generate a sample solution like so:
		boolean[] sol2 = new boolean[SOL_SIZE_2];
		for(int i=0;i< sol2.length; i++)
		{
			sol2[i]= (Math.random()>0.5);
		}
		return sol2;
	}

	public static double fitnessCalculator(boolean[] candidateSolutionBoolean)
	{
		double[] ratings = Assess.getTest2(candidateSolutionBoolean);
		double weight=ratings[0];
		double utility=ratings[1];
		
		double fitnessBoolean;

		if(weight > 500)
		{
			fitnessBoolean = - weight;
		}

		else
		{
			fitnessBoolean = utility;
		}
		return fitnessBoolean;
	}
}
