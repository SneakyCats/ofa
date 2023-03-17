package org.ofa.entretien.core.domain;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubcontactorTest {

    private OrderingCompany orderingCompany;
    private Subcontactor sub;

    @BeforeEach
    void setUp() throws Exception {
        orderingCompany = new OrderingCompany("EDF", 1000, "Lambda");
        List<Subcontactor> listSub = new ArrayList<>();
        listSub.add(new Subcontactor("A", 1));
        listSub.add(new Subcontactor("B", 2));
        listSub.add(new Subcontactor("C", 3));
        sub.addListChild(listSub);

        Subcontactor subB = sub.getSubById(2);

        List<Subcontactor> listSubB = new ArrayList<>();
        listSubB.add(new Subcontactor("D", 1));
        listSubB.add(new Subcontactor("E", 2));
        listSubB.add(new Subcontactor("F", 3));
        subB.addListChild(listSubB);


    }

}