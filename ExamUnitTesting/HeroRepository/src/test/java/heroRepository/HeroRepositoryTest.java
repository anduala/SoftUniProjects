package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTest {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    private HeroRepository heroRepository;
    private Item item;
    private Hero hero;

    @Before
    public void initialize(){
        this.heroRepository = new HeroRepository();
        this.item = new Item(10,10,10);
        this.hero = new Hero("Neal",10,item);
    }


    @Test
    public void constructorShouldCreateDataCorrectly(){
        Assert.assertEquals(0,this.heroRepository.getCount());
    }

    @Test
    public void addShouldWorkCorrectly(){
        this.heroRepository.add(hero);
        Assert.assertEquals(1,this.heroRepository.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionIfWeTryToAddEqualHeroes(){
        this.heroRepository.add(hero);
        this.heroRepository.add(hero);
    }

    @Test (expected = NullPointerException.class)
    public void removeShouldThrowExceptionIfDataDontContainsHeroName(){
        this.heroRepository.add(this.hero);
        this.heroRepository.remove("Stefan");
    }

    @Test
    public void removeShouldWorkCorrectly(){
        Item item2 = new Item(5,5,5);
        Hero hero2 = new Hero("Stefan",40,item2);
        this.heroRepository.add(this.hero);
        this.heroRepository.add(hero2);
        this.heroRepository.remove("Neal");
        Assert.assertEquals(1,this.heroRepository.getCount());
    }

    @Test
    public void heroWithHighestStrengthShouldWorkCorrect(){
        Item item2 = new Item(5,5,5);
        Hero hero2 = new Hero("Stefan",40,item2);
        this.heroRepository.add(this.hero);
        this.heroRepository.add(hero2);
        Assert.assertEquals(this.hero,this.heroRepository.getHeroWithHighestStrength());
    }

    @Test
    public void heroWithHighestAgilityShouldWorkCorrect(){
        Item item2 = new Item(5,5,5);
        Hero hero2 = new Hero("Stefan",40,item2);
        this.heroRepository.add(this.hero);
        this.heroRepository.add(hero2);
        Assert.assertEquals(this.hero,this.heroRepository.getHeroWithHighestAgility());
    }

    @Test
    public void heroWithHighestIntelligenceShouldWorkCorrect(){
        Item item2 = new Item(5,5,5);
        Hero hero2 = new Hero("Stefan",40,item2);
        this.heroRepository.add(this.hero);
        this.heroRepository.add(hero2);
        Assert.assertEquals(this.hero,this.heroRepository.getHeroWithHighestIntelligence());
    }


    @Test (expected = NullPointerException.class)
    public void heroWithHighestStrengthShouldThrowException(){
        Item item2 = new Item(0,5,5);
        Hero hero2 = new Hero("Stefan",40,item2);
        Item item1 = new Item(0,5,5);
        Hero hero1 = new Hero("Georgi",40,item2);
        this.heroRepository.add(hero1);
        this.heroRepository.add(hero2);
        this.heroRepository.getHeroWithHighestStrength();
    }

    @Test (expected = NullPointerException.class)
    public void heroWithHighestAgilityShouldThrowException(){
        Item item2 = new Item(5,0,5);
        Hero hero2 = new Hero("Stefan",40,item2);
        Item item1 = new Item(5,0,5);
        Hero hero1 = new Hero("Georgi",40,item2);
        this.heroRepository.add(hero1);
        this.heroRepository.add(hero2);
        this.heroRepository.getHeroWithHighestAgility();
    }

    @Test (expected = NullPointerException.class)
    public void heroWithHighestIntelligenceShouldThrowException(){
        Item item2 = new Item(5,5,0);
        Hero hero2 = new Hero("Stefan",40,item2);
        Item item1 = new Item(5,5,0);
        Hero hero1 = new Hero("Georgi",40,item2);
        this.heroRepository.add(hero1);
        this.heroRepository.add(hero2);
        this.heroRepository.getHeroWithHighestIntelligence();
    }

    @Test
    public void setCountShouldReturnCorrectSize() {
        Assert.assertEquals(0,this.heroRepository.getCount());
    }

    @Test
    public void toStringShouldReturnString(){
        Assert.assertEquals("",this.heroRepository.toString());
    }




}