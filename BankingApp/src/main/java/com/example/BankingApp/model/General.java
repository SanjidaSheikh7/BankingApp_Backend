package com.example.BankingApp.model;

import java.util.Arrays;

public class General {
    public static String[] getPages(int tp, int cp) {
        int lp = tp;
        int pp = 1;

        int n = 7;
        String[] ps = new String[n];
        if(tp <= n) {
            for(int i=0; i<tp; i++) {
                ps[i] = String.valueOf(i + 1);
            }
        }else {

            int fh = cp - pp;
            int sh = lp - cp;

            int r = 0;
            if (fh > 0) {
                if (fh <= 3) {
                    for (int i = 1; i <= fh; i++) {
                        ps[r++] = String.valueOf(i);
                    }
                } else {
                    ps[r++] = String.valueOf(pp);
                    ps[r++] = "...";
                    if (sh < 3) {
                        int diff = 3 - sh;
                        int phv = cp - 1;
                        for (int i = 1; i <= diff; i++) {
                            ps[r++] = String.valueOf(phv - diff);
                            phv++;
                        }
                    }
                    ps[r++] = String.valueOf(cp - 1);
                }
            }
            ps[r++] = String.valueOf(cp);

            if (sh > 0) {
                if (sh <= 3) {
                    for (int i = 1; i <= sh; i++) {
                        ps[r++] = String.valueOf(cp + i);
                    }
                } else {
                    int shv = cp;
                    for (int i = r; i < n - 2; i++) {
                        ps[i] = String.valueOf(++shv);
                    }
                    ps[n - 2] = "...";
                    ps[n - 1] = String.valueOf(lp);
                }
            }
        }

        ps = Arrays.stream(ps)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);

        return ps;
    }
}