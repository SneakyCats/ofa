package org.ofa.entretien.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderingCompanyTest {

    private OrderingCompany orderingCompany;
    private List<Subcontactor> listSub;

    @BeforeEach
    void setUp() throws Exception {
        orderingCompany = new OrderingCompany("EDF", 1000, "Lambda");
        listSub = new ArrayList<>();
        listSub.add(new Subcontactor("A", 1));
        listSub.add(new Subcontactor("B", 2));
        listSub.add(new Subcontactor("C", 3));
        orderingCompany.addListChild(listSub);
    }

    @Test
    void shouldAddSubContactorIntoOrderingCompany() throws SubContractorAlreadyAddedException, SubContractorNotRefHimselfException {
        orderingCompany.addChild(new Subcontactor("A",1));
        assertThat(orderingCompany.getListChild().get(0).getName()).isEqualTo(("Enterprise A"));
    }

    @Test
    void shouldAddManySubContactorIntoOrderingCompany() throws SubContractorAlreadyAddedException, SubContractorNotRefHimselfException {
        orderingCompany.addListChild(listSub);
        assertThat(orderingCompany.getListChild()).containsExactlyElementsOf(listSub);
    }

    @Test
    void shouldNotAddTwiceTheSameSubContractor() throws SubContractorAlreadyAddedException, SubContractorNotRefHimselfException {
        Subcontactor subA = listSub.get(0);

        assertThatThrownBy(
                () -> orderingCompany.addChild(subA))
                .isInstanceOf(SubContractorAlreadyAddedException.class);
    }

    @Test
    void shouldGetExceptionWhenOrderingCompanyAddHimselfLikeSubcontactor() throws SubContractorAlreadyAddedException, SubContractorNotRefHimselfException {
        Subcontactor subD = new Subcontactor("D", 1000);
        orderingCompany.addChild(subD);

        assertThatThrownBy(
                () -> orderingCompany.addChild(subD))
                .isInstanceOf(SubContractorNotRefHimselfException.class);
    }
}