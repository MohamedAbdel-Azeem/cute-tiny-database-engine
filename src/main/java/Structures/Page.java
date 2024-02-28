package Structures;

import Structures.Tuple;
import utils.Configurator;
import java.util.Vector;


public class Page {
    private int maxNumOfRows;
    private Vector<Tuple> tuples;

    public Page(){
        this.maxNumOfRows = Configurator.getMaxPageSize();
        System.out.println(maxNumOfRows);
        tuples = new Vector<Tuple>();
    }

    public boolean isFull(){
        return tuples.size() == maxNumOfRows;
    }

    public void addTuple(Tuple tuple){
        tuples.add(tuple);
    }

    public Vector<Tuple> getTuples(){
        return tuples;
    }
}
