package petshop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Birds implements Classification,Supplies,Runnable {

    private static final Logger logger = LogManager.getLogger(String.valueOf(Birds.class));
        public String breed;
        private long petid;
        protected int age;
        public char gender;
        private String color;
        private double birdprice;

    public Birds() {
    }

    public Birds(String breed, long petid, int age, char gender, String color, double birdprice) {
           this.breed = breed;
           this.petid = petid;
           this.age = age;
           this.gender = gender;
           this.color = color;
           this.birdprice = birdprice;
       }

       public String getBreed() {
           return breed;
       }

       public void setBreed(String breed) {
           this.breed = breed;
       }

       public long getPetid() {
           return petid;
       }

       public void setPetid(long petid) {
           this.petid = petid;
       }

       public int getAge() {
           return age;
       }

       public void setAge(int age) {
           this.age = age;
       }

       public char getGender() {
           return gender;
       }

       public void setGender(char gender) {
           this.gender = gender;
       }

       public String getColor() {
           return color;
       }

       public void setColor(String color) {
           this.color = color;
       }

       public double getBirdprice() {
           return birdprice;
       }

       public void setBirdprice(double birdprice) {
           this.birdprice = birdprice;
       }

    @Override
    public String toString() {
        return "Birds{" +
                "breed='" + breed + '\'' +
                ", petid=" + petid +
                ", age=" + age +
                ", gender=" + gender +
                ", color='" + color + '\'' +
                ", birdprice=" + birdprice +
                '}';
    }

    @Override


    public void characteristic() {

            logger.info("High power and low weight");
        }

        public void sound() {
            logger.info("Bird chirps");
        }

        public void food() {

            logger.info("Fortified Nutrition,wild bird feed and other foods are available for birds");
        }
        public void medicines()
        {
            logger.info("Bird Ferti-VIT,bird supplements and other medicines are available");
        }
        public void accessories()
        {
            logger.info("Bird cage,bird feeder and other accessories are available");

        }
        public void toys()
        {
            logger.info("Bird swing,play stand and other toys are available");
        }
        public void habitat()
        {
            logger.info("Lives in grassland,forestland,river,stream");
        }

 //Homework11 implementing Runnable interface
    @Override
    public void run() {
        logger.info("Implements Runnable Interface");
        try {
            Thread.sleep(2000);
        }catch(Exception e)
        {

        }
    }
}

