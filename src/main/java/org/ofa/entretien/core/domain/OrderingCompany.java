package org.ofa.entretien.core.domain;

import java.util.ArrayList;
import java.util.List;


public class OrderingCompany {
    private final String name;

    private final String projet;
    private final int id;
    private final List<Subcontactor> listChild;

    public OrderingCompany(String name, int id, String projet) {
        this.name = name;
        this.id = id;
        this.projet = projet;
        this.listChild = new ArrayList<>();
    }

    public void addChild(Subcontactor child) throws SubContractorAlreadyAddedException, SubContractorNotRefHimselfException {
        if (child == null) {
            throw new IllegalArgumentException("Subcontractor can't be null");
        }

        for (Subcontactor sub : listChild) {
            if (sub.getId() == child.getId()) {
                throw new SubContractorNotRefHimselfException("OrderingCompany can't be also Subcontactor");
            }
        }

        if (listChild.contains(child)) {
            throw new SubContractorAlreadyAddedException("Subcontractor " + child.getName() + " is already added for this subcontactor");
        }

        listChild.add(child);
    }

    public void addListChild(List<Subcontactor> listChild) throws SubContractorAlreadyAddedException, SubContractorNotRefHimselfException {
        for (Subcontactor subcontactor : listChild) {
            addChild(subcontactor);
        }
    }

    public String getName() {
        return name;
    }

    public List<Subcontactor> getListChild() {
        return listChild;
    }

    public int getId() {
        return id;
    }

    public String getProjet() {
        return projet;
    }

    /*public List<Subcontactor> getListSubcontactorForProject() {

    }*/
}

// Cas fonctionnelle
// 1) Impossible d'ajouter deux fois le même sous traitant
// 2) Un sous traitant ne peut pas avoir comme parent un enfant
// 3) Un sous traitant peut-etre présent dans d'autres sous traitants (sur des projets différents par exemple)
// 4) Un donneur d'ordre n'a aucun sens de faire partie des sous traitans
// 5)