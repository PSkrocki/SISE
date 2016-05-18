


public class GoodRowAndColumn implements IHeuristic {


	@Override
	public int calculateOrders(int[][] Nodearray) {
		int n = Explorer.resultArray.length;
		int m = Explorer.resultArray[0].length;
		int notGoodCol = 0;
		int notGoodRow = 0;
		int rate = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// jesli klocek nie jest na swoim miejscu
				if (Nodearray[i][j] != Explorer.resultArray[i][j]) {
					// przejdz po tablicy target, znajdz gdzie ten klocek
					// powinnien byc
					// i sprawdz czy jest w dobre kolumnie i wierszu
					for (int k = 0; k < n; k++) {
						for (int l = 0; l < m; l++) {
							if (Nodearray[i][j] == Explorer.resultArray[k][l]) {
								if (i != k) {
									notGoodCol++;
								}

								if (j != l) {
									notGoodRow++;
								}

								break;
							}
						}
					}
				}
			}
		}

		rate = notGoodCol + notGoodRow;

		return rate;	
	}
	
}