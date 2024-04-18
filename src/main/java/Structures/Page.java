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

    public void addTuple(int index,Tuple tuple){
        tuples.add(index,tuple);
    }

    public Vector<Tuple> getTuples(){
        return tuples;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Tuple tuple : tuples){
            sb.append(tuple.toString()).append(",");
        }
        return sb.toString().substring(0,sb.length()-1);
    }


}
