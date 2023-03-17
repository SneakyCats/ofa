package org.ofa.entretien.core.domain;

import java.util.ArrayList;
import java.util.List;

class Subcontactor {
    private List<Subcontactor> listSub;
    private String name;
    private int id;
    private Subcontactor parent;

    public Subcontactor() {
        listSub = new ArrayList<>();
    }

    public Subcontactor(String name, int id) {
        this.name = name;
        this.id = id;
        listSub = new ArrayList<>();
    }

    public void addChild(Subcontactor child) throws SubContractorNotRefHimselfException, SubContractorCantAddHisParent, SubContractorAlreadyAddedException {
        if (child == null) {
            throw new IllegalArgumentException("Subcontractor can't be null");
        }

        for (Subcontactor sub : listSub) {
            if (sub.getId() == child.getId()) {
                throw new SubContractorNotRefHimselfException("Subcontactor can't be also Subcontactor in the same tree");
            }
        }

        if (hasSameParent(child)) {
            throw new SubContractorCantAddHisParent("Subcontactor can't add its parent as a subcontactor");
        }

        if (listSub.contains(child)) {
            throw new SubContractorAlreadyAddedException("Subcontactor already added");
        }

        // Vérifier qu'un sous traitant n'a qu'un seul parent


        // Il est possible d'avoir un sous traitant employé par différente personne ??
        // Si oui, pas de règle mais il faut faire évoluer le modèle pour avoir plusieurs parents
        // si non, il faut s'assurer qu'un sous traitant ne peut avoir qu'un seul parent

        // Exemple :
            // A - B - C - D
            //     B - E - F
            //     C - G - H
            //         G - E

        listSub.add(child);
        child.setParent(this);
    }

    public void addListChild(List<Subcontactor> listChild) throws SubContractorAlreadyAddedException, SubContractorNotRefHimselfException, SubContractorCantAddHisParent {
        for (Subcontactor subcontactor : listChild) {
            addChild(subcontactor);
        }
    }

    private boolean hasParent(Subcontactor sub) {
        return sub.getParent() != null;
    }

    private boolean hasSameParent(Subcontactor sub) {
        while(sub.hasParent(sub)) {
            if(compareParentId(sub.getParent())) {
                return true;
            }
        }
        return false;
    }

    private boolean compareParentId(Subcontactor sub) {
        return sub.getId() == sub.getParent().getId();
    }

    public List<Subcontactor> getListSub() {
        return listSub;
    }

    public void setParent(Subcontactor parent) {
        this.parent = parent;
    }

    public Subcontactor getParent() {
        return parent;
    }

    public void setListSub(List<Subcontactor> listSub) {
        this.listSub = listSub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subcontactor getSubById(int subId) {
        for (Subcontactor subTmp : this.getListSub()) {
            if(subId == subTmp.getId()) {
                return subTmp;
            }
        }
        return null;
    }
}