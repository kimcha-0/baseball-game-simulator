public class Player {
  String name, position, LR;
  double G, PA, AB, Run, Hit, singles, doubles, triples, HR, RBI, BB, SO, SB, CS, GDP, HBP;
  double speedScore, singleAVG, doubleAVG, tripleAVG, HRAVG, BBAVG, SOAVG, HBPAVG;
  double W, L, BF, IP, pHit, pHR, pBB, pSO, SAV, pG, GS, pHBP;
  double pSingleAVG, pDoubleAVG, pTripleAVG, pHRAVG, pBBAVG, pSOAVG, pHBPAVG;
  double PO, ASS, ERR;
  double fldPCT;

  double ab, r, hit, rbi, e, ip, h, er, bb, so, hbp, to, pitchCount;

  public Player() {}

  public void setAvg() {
    fldPCT = ((PO + ASS) / (PO + ASS + ERR));
    if (PA > 0) {
      setSpeedScore();
      SOAVG = SO / PA;
      BBAVG = BB / PA;
      HBPAVG = HBP / PA;
      singleAVG = (Hit - (doubles + triples + HR)) / PA;
      doubleAVG = doubles / PA;
      tripleAVG = triples / PA;
      HRAVG = HR / PA;
    }
    if (position.equals("P")) {
      pDoubleAVG = (pHit * .174) / BF;
      pTripleAVG = (pHit * .024) / BF;
      pHRAVG = pHR / BF;
      pSingleAVG = (pHit / BF) - pDoubleAVG - pTripleAVG - pHRAVG;
      pBBAVG = pBB / BF;
      pSOAVG = pSO / BF;
      pHBPAVG = pHBP / BF;
    }
  }

  public void setSpeedScore() {
    double f1 = (SB + 3) / (SB + CS + 7);
    f1 = (f1 - .4) * 20;
    f1 = checkFactor(f1);

    double f2 = (SB + CS) / (singles + BB + HBP);
    f2 = (Math.sqrt(f2) / 0.7);
    f2 = checkFactor(f2);
    double f3 = triples / (AB - HR - SO);
    f3 = f3 / .0016;
    f3 = checkFactor(f3);

    double f4 = (Run - HR) / (Hit + BB + HBP - HR);
    f4 = (f4 - .1) * 25;
    f4 = checkFactor(f4);

    double f5 = GDP / (AB - HR - SO);
    f5 = (.063 - f5) / .007;
    f5 = checkFactor(f5);

    double f6 = getF6();
    f6 = checkFactor(f6);

    speedScore = ((f1 + f2 + f3 + f4 + f5 + f6) / 6);
  }

  private double checkFactor(double factor) {
    if (factor > 10) {
      return 10;
    } else if (factor < 0) {
      return 0;
    } else {
      return factor;
    }
  }

  private double getF6() {
    if (position.equals("P")) return 0;
    else if (position.equals("C")) return 1;
    else if (position.equals("1B")) return 2;
    else if (position.equals("2B")) return (((PO + ASS) / G) / 4.8) * 6;
    else if (position.equals("3B")) return (((PO + ASS) / G) / 2.65) * 4;
    else if (position.equals("SS")) return (((PO + ASS) / G) / 4.6) * 7;
    else return (((PO + ASS) / G) / 2.0) * 6;
  }

  public String toString() {
    return name + " (" + position + ")";
  }

  public boolean isFast() {
    return speedScore > 5;
  }

  public void clearGameStats() {
    pitchCount = ab = r = hit = rbi = e = ip = h = er = bb = hbp = to = so = 0;
  }
}
