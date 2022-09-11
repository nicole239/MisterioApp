package com.nicole.misterioapp.logic;

public class Dice {

    private int numberOfDice;

    public Dice(int numberOfDice){
        this.numberOfDice = numberOfDice;
    }

    public int ThrowDice(){
        int total = 0;
        for(int i=0; i < numberOfDice; i++){
            total += ThrowOneDie();
        }
        return total;
    }

    private int ThrowOneDie() {
        int min = 1;
        int max = 6;
        return (int) ((Math.random() * (max - min)) + min);
    }
}
