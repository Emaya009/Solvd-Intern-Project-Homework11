package petshop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.threads.Connection;
import petshop.threads.ConnectionPool;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pet extends Thread {
    private static final Logger logger = LogManager.getLogger(String.valueOf(Pet.class));

    //Homework11 extending Thread class
    public void run()
    {
        //Homework 9 Supplier built in functional interface usage in lambda expression
        List<String> petName = Arrays.asList("Dog", "Cat", "Fish", "Birds", "Reptiles");
        List<String> petNames = new ArrayList<>(petName);
        Supplier<ArrayList<String>> supplier = () -> (ArrayList<String>) petNames;
        ArrayList<String> result = supplier.get();
        for (String speciestypes : result) {
            try {
                Thread.sleep(1000);
            }catch(Exception e)
            {
            }
            logger.info(speciestypes);
        }

    }
    //Homework9 Enum - variable declaration
    Petnames petname;

    //Homework9 Enum constructor
    public Pet(Petnames petname) {
        this.petname = petname;
    }

    //Homework9 method created for custom Enum class Petnames usage
    public void printdetails() {
        switch (petname) {
            case DOG -> logger.info("Enum:Are you looking for the details of the pet animal dog");
            case CAT -> logger.info("Enum:Are you looking for the details of the pet animal cat");
            case BIRDS -> logger.info("Enum:Are you looking for the details of the pet birds");
            case FISH -> logger.info("Enum:Are you looking for the details of the pet fish");
            case REPTILES -> logger.info("Enum:Are you looking for the details of the pet reptile");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {
      //Homework11 Connection Pool with 7 threads
    /*        int poolSize = 5;
        int totalThreads = 7;
        ConnectionPool connectionPool = new ConnectionPool(poolSize);
        Thread[] threads = new Thread[totalThreads];
        for (int i = 0; i < totalThreads; i++) {
            Runnable worker = new WorkerThread(connectionPool);
            threads[i] = new Thread(worker);
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        //Homework11 Connection Pool for 7 threads using CompletableFuture and CompletionStage Interfaces
        int poolSize = 5;
        int totalThreads = 7;
        ConnectionPool connectionPool = new ConnectionPool(poolSize);
        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);
        CompletableFuture<Void>[] futures = new CompletableFuture[totalThreads];
        for (int i = 0; i < totalThreads; i++) {
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                Connection connection = null;
                try {
                    connection = connectionPool.getConnection();
                    // Use the connection for some work
                    System.out.println("Thread " + Thread.currentThread().getId() + " acquired connection: " + connection);
                    Thread.sleep(2000); // Simulating some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connectionPool.releaseConnection(connection);
                        System.out.println("Thread " + Thread.currentThread().getId() + " released connection: " + connection);
                    }
                }
                return null;
            }, executor);
            futures[i] = future;
        }
        CompletableFuture.allOf(futures).join();
        executor.shutdown();


        //Homework11 implementing runnable interface
        Runnable bird1=new Birds();
        Thread birdthread=new Thread(bird1);
        birdthread.start();
        try {
            Thread.sleep(1000);
        }catch(Exception e) {
        }


        //Homework11 Extending Thread class
        Pet petdog = new Pet(Petnames.DOG);
        petdog.printdetails();
        petdog.start();
        petdog.setName("Dog Thread");
        logger.info("Thread name: "+petdog.getName());
        logger.info("Thread status: " +petdog.getState());
        petdog.run();
        logger.info("Thread status: " +petdog.getState());
        Thread.sleep(1000);
        logger.info("Thread status: " +petdog.getState());
        Thread.yield();
        logger.info("Thread status: " +petdog.getState());
        Pet petcat = new Pet(Petnames.CAT);
        petcat.printdetails();
        petcat.start();
        petcat.setName("Cat Thread");
        logger.info("Thread name: "+petcat.getName());
        logger.info("Thread status: " +petcat.getState());
        petcat.run();
        logger.info("Thread status: " +petcat.getState());
        petcat.sleep(1050);
        logger.info("Thread status: " +petcat.getState());
        Pet petbird = new Pet(Petnames.BIRDS);
        petbird.printdetails();
        Pet petfish = new Pet(Petnames.FISH);
        petfish.printdetails();
        Pet petreptile = new Pet(Petnames.REPTILES);
        petreptile.printdetails();

        for (int i = 0; i < 5; i++) {

            logger.info("Enter the type of pet to know its details and availability");
            Scanner pettypeinput = new Scanner(System.in);
            String pettypevalue = pettypeinput.next();
            String pettype = pettypevalue.toLowerCase();

            switch (pettype) {
                case "dog" -> {
                    //Homework10 collection stream using non terminal operation distinct()
                    List<Dog> dogsList = Arrays.asList(new Dog("Dog", 10, "Yorkshire", 213, 'M', 6, 7, "Grey"),
                            new Dog("Dog", 5, "Doberman", 314, 'F', 28, 99, "Black"),
                            new Dog("Dog", 4, "Pitbull", 263, 'M', 21, 60, "Brown"),
                            new Dog("Dog", 10, "Yorkshire", 213, 'M', 6, 7, "Grey"));
                    logger.info("List of dogs: " + Arrays.asList(dogsList));
                    logger.info("Distinct dog details:");
                    Stream<Dog> distinctDogList = dogsList.stream().distinct();
                    distinctDogList.forEach(System.out::println);
                    //Homework10 collection stream using non terminal operation limit()
                    logger.info("Limit the dog details:");
                    Stream<Dog> limitDogList = dogsList.stream().limit(3);
                    limitDogList.forEach(System.out::println);
                    Dog dog = new Dog("Dog", 19);
                    dog.habitat();
                    dog.characteristic();
                    dog.sound();
                    dog.food();
                    dog.medicines();
                    dog.accessories();
                    dog.toys();
                    logger.info("Enter the dog count you need to purchase");
                    Scanner countinput = new Scanner(System.in);
                    int dogcount = countinput.nextInt();
                    logger.debug("Purchase amount: $" + dog.amountofpurchase(dogcount, dog.dogprice));
                    //Homework9 Enum class mammals methods() usage
                    Mammals rodent1 = Mammals.DOG;
                    logger.info(rodent1.rodents());
                    logger.info(rodent1.sense());
                  //Homework10 listing fieldnames,method names using reflection
                 Dog myDog=new Dog("Dog", 5,"Doberman", 314, 'F', 28, 99,"Black");
                    Field[] dogFields=myDog.getClass().getDeclaredFields();
                    for(Field field:dogFields) {
                        int fieldmodifiers=field.getModifiers();
                        logger.info("Field modifiers:" +fieldmodifiers);
                        Class fieldtype=field.getType();
                        logger.info("Field Type:" +fieldtype);
                        if (field.getName().equals("breed")) {
                            field.setAccessible(true);
                            field.set(myDog, "Bulldog");
                        }
                        logger.info(field.getName());
                    }
                    Method[] dogMethods=myDog.getClass().getDeclaredMethods();
                    for(Method dogmethod:dogMethods)
                    {
                        if(dogmethod.getName().equals("accessories"))
                        {
                            dogmethod.invoke(myDog);
                            myDog.accessories();
                        }
                        logger.info(dogmethod.getName());
                    }
                    Class<?> dogclass=Dog.class;
                    int dogmodifiers=dogclass.getModifiers();
                    logger.info("Modifiers in the class: "+dogmodifiers);
                    }
                case "cat" -> {
                    //Homework10 Using collection stream and terminal operation collect with averagingInt method to get the average age of the cat
                    List<Cat> catList = Arrays.asList(new Cat("Cat", 5, "Bengal Cat", 302, 4, 'F', "White"),
                            new Cat("Cat", 6, "Abyssinian Cat", 332, 1, 'M', "brown"),
                            new Cat("Cat", 3, "American Bobtail Cat", 343, 2, 'F', "orange"));
                    logger.info("List of cat: " + List.of(catList));
                    Double averageage = catList.stream().collect(Collectors.averagingInt(Cat::getAge));
                    logger.info(averageage);
                    //Homework10 Using collection stream and terminal operation collect with partitionBy method to split cats based on the age
                    Map<Boolean, List<Cat>> catAgesplit = catList.stream().collect(Collectors.partitioningBy(A -> A.getAge() >= 3));
                    catAgesplit.forEach((K, V) -> logger.info("Key: " + K + " Value: " + V));

                    Cat cat = new Cat("Cat", 14);
                    Cat.vaccination();
                    cat.habitat();
                    cat.characteristic();
                    cat.sound();
                    cat.food();
                    cat.medicines();
                    cat.accessories();
                    cat.toys();
                    logger.info("Enter the cat count you need to purchase");
                    Scanner catcountinput = new Scanner(System.in);
                    int catcount = catcountinput.nextInt();
                    Calculation totalcost = (int catcount1, double catprice) -> catcount * catprice;
                    logger.info("Amount of purchase:$" + totalcost.amountofpurchase(catcount, cat.catprice));

                    //Homework9 Enum class mammals methods() usage
                    Mammals rodent2 = Mammals.CAT;
                    logger.info(rodent2.rodents());
                    logger.info(rodent2.sense());

                  //Homework10 Getting the constructors of the class Cat using Reflection
                    Class<?> catobject = Class.forName("petshop.Cat");
                    //Gets all the constructors in the cat class using reflection
                    Constructor<?>[] catConstructors = catobject.getDeclaredConstructors();
                    for (int k = 0; k < catConstructors.length; k++) {
                        logger.info("Constructors in the cat class: " + catConstructors[k]);
                    }
                    //Gets only the public constructors in the cat class using reflection
                    Constructor<?>[] catConstructor = catobject.getConstructors();
                    for (int l = 0; l < catConstructor.length; l++) {
                        logger.info("Constructors in the cat class: " + catConstructor[l]);
                    }

                }
                case "birds" -> {
                    //Homework 10 Collection Streams to remove the duplicates and print the list of birds using set with terminal operator collect
                    List<Birds> birdsList = Arrays.asList(new Birds("Parakeet", 114, 1, 'F', "yellow", 12.50),
                            new Birds("Parakeet", 104, 2, 'M', "Blue", 14.00),
                            new Birds("Love birds", 124, 1, 'F', "Green", 15.50),
                            new Birds("Love birds", 124, 1, 'F', "Green", 15.50));
                    logger.info("List of birds: " + List.of(birdsList));
                    Set<String> birdslist = birdsList.stream().map(Birds::getBreed).collect(Collectors.toSet());
                    birdslist.forEach((System.out::println));

                    //Using colection stream to list the birdnames by comma seperated using joining method
                    String allBirds = birdsList.stream().map(Birds::getBreed).collect(Collectors.joining(","));
                    logger.info(allBirds);

                    Birds bird = new Birds();
                    bird.habitat();
                    bird.characteristic();
                    bird.sound();
                    bird.food();
                    bird.medicines();
                    bird.accessories();
                    bird.toys();
                    logger.info("Enter the bird count you need to purchase");
                    Scanner birdcountinput = new Scanner(System.in);
                    int birdcount = birdcountinput.nextInt();

                    //Homework9 Custom Lambda Expression with Functional Interface Calculation
                    Calculation totalcost = (int birdcount1, double birdprice) -> birdcount * birdprice;
                    logger.info("Amount of purchase:$" + totalcost.amountofpurchase(birdcount, bird.getBirdprice()));
                }
                case "fish" -> {

                    //Homework10 collection Stream with terminal operator collect
                    List<Fish> fishList = Arrays.asList(new Fish("Golden Fish", "Golden and Yellow"),
                            new Fish("Zebra Fish", "Black and white"),
                            new Fish("Guppies", "Colourful", 8.5));
                    logger.info("List of fish: " + List.of(fishList));
                    List<String> fishdetails = fishList.stream().map(Fish::getBreed).collect(Collectors.toList());
                    fishdetails.forEach(System.out::println);
                    Fish fish1 = new Fish();
                    Aquaticbehaviour.swim();
                    fish1.sustainability();
                    logger.info("Enter the fish count you need to purchase");
                    Scanner fishcountinput = new Scanner(System.in);
                    int fishcount = fishcountinput.nextInt();
                    logger.debug("Purchase amount: $" + fish1.amountofpurchase(fishcount, 4.66));

                    //Homework10 Getting the return type of method amountofpurchase using reflection
                   try {
                       Class<?> fishClass = Fish.class;
                       Method fishmethod = fishClass.getMethod("amountofpurchase", int.class, double.class);
                       Type fishreturntype = fishmethod.getGenericReturnType();
                       logger.info("Return types: " + fishreturntype);
                       if (fishreturntype instanceof ParameterizedType) {
                           ParameterizedType parameterizedtype = (ParameterizedType) fishreturntype;
                           Type[] typeArguments = parameterizedtype.getActualTypeArguments();
                           for (Type typeArgument : typeArguments) {
                               Class typeArgClass = (Class) typeArgument;
                               logger.info("Argument type:" + typeArgClass);
                               logger.info("Argument type name: " + typeArgClass.getName());

                           }
                       }
                       //Getting modifiers of Fish class using Reflection
                       int fishmodifiers = fishClass.getModifiers();
                       logger.info("Modifiers in fish class: " + fishmodifiers);
                       if (Modifier.isAbstract(fishmodifiers)) {
                           logger.info("Class fish is not declared as -Abstract");
                       }
                       if (Modifier.isFinal(fishmodifiers)) {
                           logger.info("Fish Class is declared as Final");
                       }
                       if (!Modifier.isStatic(fishmodifiers)) {
                           logger.info("Class or method is not declared as Static");
                       }
                   }catch(NoSuchMethodException | SecurityException e)
                    {
                        e.printStackTrace();
                    }
                }
                case "reptiles" -> {
                    //Homework10 Collection stream with filter,map and Terminal operator forEach
                    List<Reptiles> reptilenames = Arrays.asList(new Reptiles("Snake", 3, "Rattlesnake", 4.5, "Poisonous"),
                            new Reptiles("Snake", 2, "Cornsnake", 8, "Nonpoisonous"),
                            new Reptiles("Lizard Family", 5, "Chameleon", 3.4, "Nonpoisonous"));
                    logger.info("List of reptiles:" + Arrays.asList(reptilenames));
                    logger.info("Filter only snakes in the list:");
                    reptilenames.stream().filter(s -> s.getSpecies() == "Snake")
                            .map(Reptiles::getReptilename)
                            .forEach(System.out::println);
                    Map<String, List<Reptiles>> mapreptilelist = reptilenames.stream().collect(Collectors.groupingBy(Reptiles::getSpecies));
                    mapreptilelist.forEach((K, V) -> logger.info("Key= " + K + "Value= " + V));

                }
                default -> {
                    logger.info("Pet not available");
                    try {
                        throw new InvalidPetInputException("Invalid pet input pls enter a valid pettype: Dog/Cat/Birds/Fish/Snake");
                    } catch (InvalidPetInputException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

            Newpets newpet1=Newpets.RABBIT;
            logger.info("You have selected a " +newpet1.getname() +" for " +newpet1.getprice());
            logger.info("Enter the Rabbit count you need to purchase");
            Scanner rabbitcountinput = new Scanner(System.in);
            int rabbitcount = rabbitcountinput.nextInt();
           //Homework9 custom Enum class calculation usage with lambda expression
           Calculation totalcost1 = (int rabbitcount1, double rabbitprice) -> rabbitcount1 * rabbitprice;
           logger.info("Amount of purchase:$" + totalcost1.amountofpurchase(rabbitcount, newpet1.getprice()));
          //Homework9 Enum class mammals methods() usage
            Mammals rodent3 = Mammals.RABBIT;
            logger.info(rodent3.rodents());
            logger.info(rodent3.sense());

        //Homework9 custom Enum class Newpets with lambda expression
           Newpets newpet2=Newpets.GUINEAPIG;
           logger.info("You have selected a " +newpet2.getname() +" for " +newpet2.getprice());
           logger.info("Enter the Guineapig count you need to purchase");
           Scanner guineapigcountinput = new Scanner(System.in);
           int guineapigcount = guineapigcountinput.nextInt();
        //Homework9 custom Enum class calculation usage with lambda expression
          Calculation totalcost2 = (int guineapigcount1, double guineapigprice) -> guineapigcount1 * guineapigprice;
          logger.info("Amount of purchase:$" + totalcost2.amountofpurchase(guineapigcount, newpet2.getprice()));
        //Homework9 Enum class mammals methods() usage
          Mammals rodent4 = Mammals.RABBIT;
          logger.info(rodent4.rodents());
          logger.info(rodent4.sense());

          //Homework9 custom Enum class Newpets with lambda expression
          Newpets newpet3=Newpets.HAMSTER;
          logger.info("You have selected a " +newpet3.getname() +" for " +newpet3.getprice());
          logger.info("Enter the Hamster count you need to purchase");
          Scanner hamstercountinput = new Scanner(System.in);
          int hamstercount = hamstercountinput.nextInt();
          //Homework9 custom Enum class calculation usage with lambda expression
          Calculation totalcost3 = (int hamstercount1, double hamsterprice) -> hamstercount1 * hamsterprice;
          logger.info("Amount of purchase:$" + totalcost3.amountofpurchase(hamstercount, newpet3.getprice()));
          //Homework9 Enum class mammals methods() usage
          Mammals rodent5 = Mammals.HAMSTER;
          logger.info(rodent5.rodents());
          logger.info(rodent5.sense());

          //Homework9 custom Enum class Newpets with lambda expression
          Newpets newpet4=Newpets.MOUSE;
          logger.info("You have selected a " +newpet4.getname() +" for " +newpet4.getprice());
          logger.info("Enter the Mouse count you need to purchase");
          Scanner mousecountinput = new Scanner(System.in);
          int mousecount = mousecountinput.nextInt();
          //Homework9 custom Enum class calculation usage with lambda expression
          Calculation totalcost4 = (int mousecount1, double mouseprice) -> mousecount1 * mouseprice;
          logger.info("Amount of purchase:$" + totalcost4.amountofpurchase(mousecount, newpet4.getprice()));
          //Homework9 Enum class mammals methods() usage
           Mammals rodent6 = Mammals.MOUSE;
           logger.info(rodent6.rodents());
           logger.info(rodent6.sense());
    }
    }


