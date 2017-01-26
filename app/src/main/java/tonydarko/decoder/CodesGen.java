package tonydarko.decoder;

import java.util.LinkedHashMap;
import java.util.Map;

public class CodesGen {

    Map<Character, String> hm = new LinkedHashMap<>();
    int systemIschesl, bites;

    public CodesGen(int systemIschesl, int bites) {

        this.systemIschesl = systemIschesl;
        this.bites = bites;

        int[] mass = new int[bites];
        char sym = 'А';

        for (int i = 0; i < 97; i++) {
            if (mass[2] == systemIschesl) {
                mass[1]++;
                mass[2] = 0;
            }
            if (mass[1] == systemIschesl) {
                mass[0]++;
                mass[1] = 0;
            }

            if (i == 64) {
                sym = ' ';
            }

            hm.put(sym, "" + mass[0] + "" + mass[1] + "" + mass[2]);
            sym++;
            mass[2]++;
        }
    }

}
