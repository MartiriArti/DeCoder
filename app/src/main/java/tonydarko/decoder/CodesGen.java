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
        int n = bites - 1;

        for (int i = 0; i < 97; i++) {
            for (int c = n; c >= 0; c--) {
                if (mass[c] == systemIschesl) {
                    mass[c-1]++;
                    mass[c] = 0;
                }
            }
            if (i == 64) {
                sym = ' ';
            }

            String mas = "";
            for (int t = 0; t < bites; t++){
                mas += "" + mass[t];
            }
            hm.put(sym,mas);
            sym++;
            mass[bites-1]++;
        }
    }

}
