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
        System.out.println("Page Size: "+tuples.size() + " Max Size: "+maxNumOfRows);
        return tuples.size() == maxNumOfRows;
    }

    public void addTuple(Tuple tuple){
        tuples.addFirst(tuple);
    }

    public Vector<Tuple> getTuples(){
        return tuples;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Tuple tuple : tuples){
            sb.append(tuple.toString());
        }
        return sb.toString();
    }


}
