
public class Manhattan implements IHeuristic {

	@Override 
	public int calculateOrders(int[][] nodeArray) {
		int n = Explorer.resultArray.length;
		int m = Explorer.resultArray[0].length;
		int rate = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// jesli klocek nie jest na swoim miejscu
				if (nodeArray[i][j] != Explorer.resultArray[i][j]) {
					// przejdz po tablicy target, znajdz gdzie ten klocek
					// powinnien byc
					// i sprawdz jak daleko jest od swojego prawidlowego miejsca
					for (int k = 0; k < n; k++) {
						for (int l = 0; l < m; l++) {
							if (nodeArray[i][j] == Explorer.resultArray[k][l]) {
								rate = rate + Math.abs(k - i) + Math.abs(l - j);
								break;
							}
						}
					}
				}
			}
		}
		return rate;
	}

}
