package Structures;

import Utils.Configurator;

import java.io.Serializable;
import java.util.Vector;


public class Page implements Serializable {
    private int maxNumOfRows;
    private Vector<Tuple> tuples;

    public Page(){
        this.maxNumOfRows = Configurator.getMaxPageSize();
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
