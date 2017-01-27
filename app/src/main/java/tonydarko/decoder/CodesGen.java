package tonydarko.decoder;

import java.util.LinkedHashMap;
import java.util.Map;

public class CodesGen {

    Map<Character, String> hm = new LinkedHashMap<>();
    int systemIschesl, bites, sum = 0;
    Boolean Latin, Kiril, Num, Symb;
    int[] mass;
    char sym;

    public CodesGen(int systemIschesl, int bites, Boolean Latin, Boolean Kiril, Boolean Num, Boolean Symb) {
        this.Latin = Latin;
        this.Kiril = Kiril;
        this.Num = Num;
        this.Symb = Symb;
        System.out.println("CG: " + Latin + Kiril + Num + Symb);

        this.systemIschesl = systemIschesl;
        this.bites = bites;

        mass = new int[bites];

        if (Latin){
            sym = 'A';
            sum = 26;
            Gen(sym, sum);
            sym = 'a';
            sum = 26;
            Gen(sym, sum);
        }
        if (Kiril){
            sym = 'А';
            sum = 64;
            Gen(sym, sum);
        }
        if (Num){
            sym = '0';
            sum = 10;
            Gen(sym, sum);
        }
        if (Symb){
            sym = ' ';
            sum = 15;
            Gen(sym, sum);
        }

    }

    public void Gen(char sym, int sum){
        int n = bites - 1;

        for (int i = 0; i < sum; i++) {
            for (int c = n; c >= 0; c--) {
                if (mass[c] == systemIschesl) {
                    mass[c-1]++;
                    mass[c] = 0;
                }
            }

            String mas = "";
            for (int t = 0; t < bites; t++){
                mas += "" + mass[t];
            }
            hm.put(sym,mas);
            System.out.println(sym + " " + mas);

            sym++;
            mass[bites-1]++;
        }
    }

}
