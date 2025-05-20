package de.unibayreuth.se.campuscoffee.domain;

import de.unibayreuth.se.campuscoffee.domain.ports.PosService;
import org.apache.commons.lang3.SerializationUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TestFixtures {
    private static final List<Pos> POS_LIST = List.of(
            new Pos("CrazySheep (RW-I)", "", PosType.CAFE, CampusType.MAIN, "Andreas-Maisel-Weg", "2", 95445, "Bayreuth"),
            new Pos("Cafeteria (Mensa)", "", PosType.CAFE, CampusType.MAIN, "Universitätsstraße", "30", 95447, "Bayreuth"),
            new Pos("Lidl (Nürnberger Str.)", "", PosType.VENDING_MACHINE, CampusType.ZAPF, "Nürnberger Str.", "3a", 95448, "Bayreuth")
    );

    public static List<Pos> getPosList() {
        return POS_LIST.stream()
                .map(SerializationUtils::clone) // prevent issues when tests modify the fixture objects
                .toList();
    }

    public static List<Pos> createPos(PosService posService) {
        return getPosList().stream()
                .map(posService::upsert)
                .collect(Collectors.toList());
    }
}
