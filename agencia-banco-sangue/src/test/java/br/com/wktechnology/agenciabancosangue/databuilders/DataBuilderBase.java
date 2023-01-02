package br.com.wktechnology.agenciabancosangue.databuilders;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public abstract class DataBuilderBase<T> {

    protected Faker faker;

    public abstract T build();

    public DataBuilderBase() {
        faker = new Faker();
    }

    public List<T> buildList(Integer length) {
        List<T> objects = new ArrayList<>();

        for (int size=1; size <= length; size++) {
            objects.add(this.build());
        }

        return objects;
    }
}
