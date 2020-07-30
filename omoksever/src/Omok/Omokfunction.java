package Omok;

import java.util.Arrays;

public class Omokfunction {
	
	private static int[][] arr;
	private final static int Black = -1;
	private final static int White = 1;
	private static boolean checkBW = true;
	
	public Omokfunction() {
	}
	
	public static int[][] getArr() {
		return arr;
	}

	public static void setArr(int[][] arr) {
		Omokfunction.arr = arr;
	}

	public static void Omokarray(){
		arr = new int[15][15];
		for(int i=0;i<arr.length;i++) {
			for(int j = 0; j<arr[i].length;j++) {
				arr[i][j]= 0;
			}
		}
	}
	public static void startblack() {
		checkBW = true;
	}
	public static int getBlack() {
		  return Black;
		 }
	public static int getWhite() {
		  return White;
		 }
	public static int getXY(int x, int y) {
		return arr[x][y]; 
	}
	public static void turnchange() {
		if(checkBW)
			checkBW = false;
		else
			checkBW = true;
		
	}
	 public boolean getCheck() {
		  return checkBW;
		 }
	public static void CheckXY(int x,int y) {
		if(checkBW) {
			arr[x][y] = Black;
		}else {
			arr[x][y] = White;
		}
	}
	public static void ChangeXY(int x,int y) {
		if(checkBW) {
			arr[x][y] = 0;
		}else {
			arr[x][y] = 0;
		}
	}
	public boolean winCheck(int x,int y){
		if(winCheckUp(x, y)||winCheckDown(x, y)||winCheckRU(x,y)||winCheckLU(x,y)||winCheckR(x,y)||winCheckL(x,y)
				||winCheckRD(x,y)||winCheckLD(x,y)||winCheckOneUp(x,y)||winCheckOneDown(x,y)||winCheckOneR(x,y)
				||winCheckOneL(x,y)||winCheckOneRU(x,y)||winCheckOneLU(x,y)||winCheckOneRD(x,y)
				||winCheckOneLD(x,y)||winCheckCenterUD(x,y)||winCheckCenterRL(x,y)||winCheckCenterRU(x,y)||winCheckCenterLU(x,y))
			return true;
		else
			return false;
	}
	public int samsam(int x, int y) {
		int sam = 0;
		sam += rightleft33(x,y);
		sam += updown33(x,y);	
		sam += rightdiagonal33(x, y);
		sam += leftdiagonal33(x, y);		
		if(sam >= 2) {return sam;}
		return 0;
	}
	//위쪽 (아래 확인) (끝) 
	public boolean winCheckUp(int x,int y) { 
		try{
			for(int i=y;i<y+5;i++){  // 아래로 5개가 같은지 확인  
				if(arr[x][y]!=arr[x][i])
					return false;
				}
			// 육 방지 코드 (한칸 위가 같은색이면 6) (아래로 6번째가 같은 색이면 6)
			if(arr[x][y] == arr[x][y-1] || arr[x][y] == arr[x][y+5]) {
				return false;}
			System.out.println("占쎌맄  ");
			return true;
		}catch(ArrayIndexOutOfBoundsException e){
			if(y==0) {
				for(int i=y;i<y+5;i++){ // 아래로 5개가 같은지 확인 + 육 방지
				if(arr[x][y]!=arr[x][i])
					return false;
				}
				if(arr[x][y] == arr[x][y+5]) {
					return false;}
			return true;
			}
			if(y==10) {
				for(int i=y;i<y+5;i++){ // 아래로 5개가 같은지 확인 
				if(arr[x][y]!=arr[x][i])
					return false;
				}
			return true;
			}
			return false;
		}
	}
	//占쎈툡占쎌삋筌잞옙 (占쎌맄嚥∽옙 占쎌넇占쎌뵥) (占쎄국) 
	public boolean winCheckDown(int x,int y) {
		try {
			for(int i=y;i>y-5;i--){ // 占쎌맄嚥∽옙 5揶쏆뮄占� 揶쏆늿占쏙쭪占� 占쎌넇占쎌뵥 
				if(arr[x][y]!=arr[x][i])
					return false;
			}
			// 占쎌몓 獄쎻뫗占� �굜遺얜굡 (占쎈립燁삼옙 占쎈툡占쎌삋揶쏉옙 揶쏆늿占쏙옙源뗰옙�뵠筌롳옙 6) (占쎌맄嚥∽옙 6甕곕뜆�럮揶쏉옙 揶쏆늿占� 占쎄퉳占쎌뵠筌롳옙 6)
			if(arr[x][y] == arr[x][y+1] || arr[x][y] == arr[x][y-5]) {
				return false; }
			System.out.println("占쎈툡 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			if(y==4) {
			for(int i=y;i>y-5;i--){ // 占쎌맄嚥∽옙 5揶쏆뮄占� 揶쏆늿占쏙쭪占� 占쎌넇占쎌뵥 
				if(arr[x][y]!=arr[x][i])
					return false;
			}
			return true;
			}
			if(y==14) {
			for(int i=y;i>y-5;i--){ // 占쎌맄嚥∽옙 5揶쏆뮄占� 揶쏆늿占쏙쭪占� 占쎌넇占쎌뵥  + 占쎌몓 獄쏉옙
				if(arr[x][y]!=arr[x][i])
					return false;
			}
			if(arr[x][y] == arr[x][y-5]) {
				return false; }
			return true;
			}
			return false;
		}
	}
	//占쎌궎�몴紐꾠걹 (占쎌뇢筌잛럩�몵嚥∽옙 占쎌넇占쎌뵥)  (占쎄국) 
	public boolean winCheckR(int x,int y) { //x筌앹빓占� 
		try {
			for(int i = x ; i > x-5;i--){
				if(arr[x][y]!=arr[i][y])
					return false;
			}
			// 占쎌몓 獄쎻뫗占� �굜遺얜굡 (占쎈립燁삼옙 占쎌궎�몴紐꾠걹占쎌뵠 揶쏆늿占쏙옙源뗰옙�뵠筌롳옙 6) (占쎌뇢筌잛럩�몵嚥∽옙 6甕곕뜆�럮揶쏉옙 揶쏆늿占� 占쎄퉳占쎌뵠筌롳옙 6)
			if(arr[x][y] == arr[x+1][y] || arr[x][y] == arr[x-5][y]) {
				return false; }
			System.out.println("占쎌궎 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			if(x==4) { // 占쎌뇢筌잛럩�몵嚥∽옙 5揶쏆뮄占� 揶쏆늿占쏙쭪占� 占쎌넇占쎌뵥
				for(int i = x ; i > x-5;i--){
					if(arr[x][y]!=arr[i][y])
					return false;
			}
			return true;
			}
			if(x==14) { // 占쎌뇢筌잛럩�몵嚥∽옙 5揶쏆뮄占� 揶쏆늿占쏙쭪占� 占쎌넇占쎌뵥  + 占쎌몓 獄쎻뫗占� 
				for(int i = x ; i > x-5;i--){
					if(arr[x][y]!=arr[i][y])
					return false;
			}
				if(arr[x][y] == arr[x-5][y]) {
					return false; }
			return true;
			}
			return false;
		}
	}
	//占쎌뇢筌잛��넇占쎌뵥 (占쎄국) 
	public boolean winCheckL(int x,int y) { //x揶쏅Ŋ�꺖 
		try {
			for(int i = x ; i<x+5 ; i++){
				if(arr[x][y]!=arr[i][y])
					return false;
			}
			// 占쎌몓 獄쎻뫗占� �굜遺얜굡 (占쎈립燁삼옙 占쎌궎�몴紐꾠걹占쎌뵠 揶쏆늿占쏙옙源뗰옙�뵠筌롳옙 6) (占쎌뇢筌잛럩�몵嚥∽옙 6甕곕뜆�럮揶쏉옙 揶쏆늿占� 占쎄퉳占쎌뵠筌롳옙 6)
			if(arr[x][y] == arr[x-1][y] || arr[x][y] == arr[x+5][y]) {
				return false; }
			System.out.println("占쎌뇢 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			if(x==0) { // 占쎌궎�몴紐꾠걹占쎌몵嚥∽옙 5揶쏆뮄占� 揶쏆늿占쏙쭪占� 占쎌넇占쎌뵥  + 占쎌몓 獄쎻뫗占� 
				for(int i = x ; i<x+5 ; i++){
					if(arr[x][y]!=arr[i][y])
						return false;
			}
				if(arr[x][y] == arr[x+5][y]) {
					return false; }
			return true;
			}
			if(x==10) { // 占쎌궎�몴紐꾠걹占쎌몵嚥∽옙 5揶쏆뮄占� 揶쏆늿占쏙쭪占� 占쎌넇占쎌뵥 
				for(int i = x ; i<x+5 ; i++){
					if(arr[x][y]!=arr[i][y])
						return false;
			}
			return true;
			}
			return false;
		
		}
	}
	//占쎌궎�몴紐꾠걹 占쎌맄 占쏙옙揶쏄낯苑� �빊�뮆而� (占쎄국)
	public boolean winCheckRU(int x,int y) { //x- 揶쏅Ŋ�꺖 y -筌앹빓占� 
		try {
			//占쎌궎�몴紐꾠걹 占쎌맄占쎈퓠占쎄퐣 占쎈툡占쎌삋嚥∽옙 占쏙옙揶쏄낯苑� 筌ｋ똾寃� 
			for(int i=x, j=y; i > x-5 ;i--,j++){ 
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			//占쎌몓筌륅옙 獄쎻뫗占� �굜遺얜굡 
			if(arr[x][y] == arr[x+1][y-1] || arr[x][y] == arr[x-5][y+5]) { 
				return false; }
			System.out.println("占쎌궎占쏙옙 ");
			return true;
			//占쎌몓筌륅옙 獄쎻뫗占� 占쎌뵥占쎈쑔占쎈뮞 占쎈퓠占쎌쑎
		} catch (ArrayIndexOutOfBoundsException e) {
			try { // 占쎌뵬占쎌뵥占쎈퓠 椰꾨챷�뒏 野껋럩�뒭 占쎈퓠占쎌쑎 占쎈퉸野껓옙 
			if((y==0&x==4)|(y==10&x==14)) {
				//占쎈퉸占쎈뼣 占쎄퐨占쎈퓠占쎄퐣 占쎈뻻占쎌삂占쎈뻻 5揶쏆뮆彛� 占쎌넇
				for(int i=x, j=y; i > x-5 ;i--,j++){
					if(arr[x][y]!=arr[i][j])
						return false;}
			return true;
				}
			if(x==14|y==0) {
				//占쎈퉸占쎈뼣 占쎄퐨占쎈퓠占쎄퐣 占쎈뻻占쎌삂占쎈뻻 5揶쏆뮆彛� 占쎌넇
				for(int i=x, j=y; i > x-5 ;i--,j++){
					if(arr[x][y]!=arr[i][j])
						return false;}
				//占쎈뼄占쎄숱揶쏆뮇�뵥筌욑옙 占쎌넇占쎌뵥
				if(arr[x][y] == arr[x-5][y+5]) { 
					System.out.println("df1");
					return false; }
			System.out.println("占쎌궎占쎌궎占쏙옙 ");
			return true;
				}
			if(y==10|x==4) {
				//占쎈퉸占쎈뼣 占쎄퐨占쎈퓠占쎄퐣 占쎈뻻占쎌삂占쎈뻻 5揶쏆뮆彛� 占쎌넇
				for(int i=x, j=y; i > x-5 ;i--,j++){
					if(arr[x][y]!=arr[i][j])
						return false;}
				//占쎈뼄占쎄숱揶쏆뮇�뵥筌욑옙 占쎌넇占쎌뵥
				if(arr[x][y] == arr[x+1][y-1]) { 
					System.out.println("df2");
					return false; }
			System.out.println("占쎌궎占쎌궎占쏙옙 ");
			return true;
				}
			return false;
			}
			// 占쎌뵥占쎈쑔占쎈뮞 占쎈퓠占쎌쑎 占쎈퉸野껓옙 
			catch(ArrayIndexOutOfBoundsException e1){
				return false;
			}
			}
		}
	//占쎌뇢筌잞옙 占쎌맄 占쏙옙揶쏄낯苑� �빊�뮆而� (占쎄국) 
	public boolean winCheckLU(int x,int y) { //x-筌앹빓占� y-筌앹빓占� 
		try {
			for(int i=x, j=y ; i < x+5 ; i++,j++){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			if(arr[x][y] == arr[x-1][y-1] || arr[x][y] == arr[x+5][y+5]) {
				return false; }
			System.out.println("占쎌뇢占쏙옙 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		try {
			if((y==0&x==10)|(y==10&x==0)) { //�뤃�딄퐤 占쎈あ �⑤끃�뱽 5揶쏆뮆彛� 占쎌넇占쎌뵥 
				for(int i=x, j=y ; i < x+5 ; i++,j++){
					if(arr[x][y]!=arr[i][j])
						return false;
			}
			System.out.println("dsfd");
			return true;
			}
			if(y==0||x==0) { //占쎌뇢筌잞옙 占쎈툡占쎌삋 占쎌몓 占쎌넇占쎌뵥 
				for(int i=x, j=y ; i < x+5 ; i++,j++){
					if(arr[x][y]!=arr[i][j])
						return false;
			}
			if(arr[x][y] == arr[x+5][y+5]) {
				return false; }
			System.out.println("ADFS");
			return true;
			}
			if(y==10|x==10) { // 占쎌궎�몴紐꾠걹 占쎌맄 占쎌넇占쎌뵥 
				for(int i=x, j=y ; i < x+5 ; i++,j++){
					if(arr[x][y]!=arr[i][j])
						return false;
			}
			if(arr[x][y] == arr[x-1][y-1]) {
				return false; }
			System.out.println("dsf");
			return true;
			}
			return false;
		}
		catch(ArrayIndexOutOfBoundsException e1) {
			return false;
		}
	}
	}
	//占쎌뇢筌잞옙 占쎈툡占쎌삋 占쏙옙揶쏄낯苑� �빊�뮆而� (占쎄국)
	public boolean winCheckRD(int x,int y) { //x- 筌앹빓占�  y - 揶쏅Ŋ�꺖    
		try {
			for(int i=x, j=y; i < x+5 ;i++,j--){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			if(arr[x][y] == arr[x-1][y+1] || arr[x][y] == arr[x+5][y-5]) {
				return false; }
			System.out.println("占쎌뇢占쎈툡占쏙옙 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				if((x==0&y==4)|(x==10&y==14)) {
					for(int i=x, j=y; i < x+5 ;i++,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				System.out.println("占쎌뇢占쎌뇢占쎈툡占쏙옙 ");
				return true;
				}
				if(x==0|y==14) {
					for(int i=x, j=y; i < x+5 ;i++,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x+5][y-5]) {
					return false; }
				System.out.println("占쎌뇢占쎌뇢占쎈툡占쏙옙 ");
				return true;
				}
				if(x==10|y==4) {
					for(int i=x, j=y; i < x+5 ;i++,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x-1][y+1]) {
					return false; }
				System.out.println("占쎌뇢占쎌뇢占쎈툡占쏙옙 ");
				return true;
				}
				return false;
			}
			
			catch(ArrayIndexOutOfBoundsException e1) {
				return false;
			}
		}
	}
	//占쎌궎�몴紐꾠걹 占쎈툡占쎌삋 占쏙옙揶쏄낯苑� �빊�뮆而� (占쎄국) 
	public boolean winCheckLD(int x,int y) { //x -揶쏅Ŋ�꺖 y - 揶쏅Ŋ�꺖 
		try {
			for(int i=x, j=y;i > x-5;i--,j--){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			if(arr[x][y] == arr[x+1][y+1] || arr[x][y] == arr[x-5][y-5]) {
				return false; }
			System.out.println("占쎌궎占쎈툡占쏙옙 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				if((x==4&y==14)|(x==14&y==4)) { //�뤃�딄퐤 占쎈あ �⑤끃�뱽 5揶쏆뮆彛� 占쎌넇占쎌뵥 
					for(int i=x, j=y;i > x-5;i--,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				return true;
				}
				if(x==14|y==14) {
					for(int i=x, j=y;i > x-5;i--,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x-5][y-5]) {
					return false; }
				return true;
				}
				if(x==4|y==4) {
					for(int i=x, j=y;i > x-5;i--,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x+1][y+1]) {
					return false; }
				return true;
				}
				return false;
				}
			catch(ArrayIndexOutOfBoundsException e1) {
				return false;
			}
		}
	}
	//占쎈립燁삼옙 占쎌맄筌잞옙 (占쎌맄嚥∽옙 占쎈릭占쎄돌 占쎌넇占쎌뵥占쎈릭�⑨옙 占쎈툡占쎌삋 占쎌넇占쎌뵥) (占쎄국)
	public boolean winCheckOneUp(int x,int y) {
		try{
			for(int i=y-1;i <y+4;i++){ //占쎈탣占쎌쑎占쎈뮞占쏙옙 獄쏆꼶占� y-1
				if(arr[x][y]!=arr[x][i])
					return false;
			}
			// 占쎌몓 獄쎻뫗占� �굜遺얜굡 (占쎈あ燁삼옙 占쎌맄揶쏉옙 揶쏆늿占쏙옙源뗰옙�뵠筌롳옙 6) (占쎈툡占쎌삋嚥∽옙 4甕곕뜆�럮揶쏉옙 揶쏆늿占� 占쎄퉳占쎌뵠筌롳옙 6)
			if(arr[x][y] == arr[x][y-2] || arr[x][y] == arr[x][y+4]) {
				return false; }
			System.out.println("占쎈립占쎌맄 ");
			return true;
		}catch(ArrayIndexOutOfBoundsException e){
			//占쎌몓 �굜遺얜굡嚥∽옙 占쎌뵥占쎈쑔占쎈뮞 占쎄퐜占쎈선揶쏅뜄釉� 
			if(y==1) {
				for(int i=y-1;i <y+4;i++){ //5揶쏆뮆彛� 占쎌넇占쎌뵥占쎈릭疫뀐옙 + 獄쏅쵐�몵嚥∽옙 占쎌몓 占쎌넇占쎌뵥 
					if(arr[x][y]!=arr[x][i])
						return false;
				}
				if(arr[x][y] == arr[x][y+4]) {
					return false; }
			System.out.println("占쎈립占쎈립占쎌맄 ");
			return true;
			}
			//占쎌몓 �굜遺얜굡嚥∽옙 占쎌뵥占쎈쑔占쎈뮞 占쎄퐜占쎈선揶쏅뜄釉� 
			if(y==11) {
				for(int i=y-1;i <y+4;i++){ //5揶쏆뮆彛� 占쎌넇占쎌뵥占쎈릭疫뀐옙 
					if(arr[x][y]!=arr[x][i])
						return false;
				}
			System.out.println("占쎈립占쎈립占쎌맄 ");
			return true;
			}
			return false;
		}
	}
	//占쎈립燁삼옙 占쎈툡占쎌삋筌잞옙 (占쎈툡占쎌삋 占쎈릭占쎄돌 占쎌넇占쎌뵥占쎈릭�⑨옙 占쎌맄 占쎌넇占쎌뵥) (占쎄국)
	public boolean winCheckOneDown(int x,int y) {
		try {
			for(int i=y+1;i>y-4;i--){
				if(arr[x][y]!=arr[x][i])
					return false;
			}
			// 占쎌몓 獄쎻뫗占� �굜遺얜굡 (占쎈あ燁삼옙 占쎈툡占쎌삋揶쏉옙 揶쏆늿占쏙옙源뗰옙�뵠筌롳옙 6) (占쎌맄嚥∽옙 4甕곕뜆�럮揶쏉옙 揶쏆늿占� 占쎄퉳占쎌뵠筌롳옙 6)
			if(arr[x][y] == arr[x][y+2] || arr[x][y] == arr[x][y-4]) {
				return false; }
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			//占쎌몓 �굜遺얜굡嚥∽옙 占쎌뵥占쎈쑔占쎈뮞 占쎄퐜占쎈선揶쏅뜄釉� 
			if(y==3) { //5揶쏆뮆彛� 占쎌넇占쎌뵥占쎈릭疫뀐옙 
				for(int i=y+1;i>y-4;i--){
					if(arr[x][y]!=arr[x][i])
						return false;
				}
			return true;
			}
			if(y==13) { //5揶쏆뮆彛� 占쎌넇占쎌뵥占쎈릭疫뀐옙 + 占쎌맄嚥∽옙 占쎌몓 占쎌넇占쎌뵥 
				for(int i=y+1;i>y-4;i--){
					if(arr[x][y]!=arr[x][i])
						return false;
				}
				if(arr[x][y] == arr[x][y-4]) {
					return false; }
			return true;
			}
			return false;
		}
	}
	//占쎈립燁삼옙 占쎌궎�몴紐꾠걹 占쎌뵠占쎈짗 占쎌뜎 占쎌뇢筌잞옙 占쎌넇占쎌뵥  (占쎄국) 
	public boolean winCheckOneR(int x,int y) {//x筌앹빓占� 占쎌뵠占쎈짗
		try {
			for(int i=x+1;i>x-4;i--){
				if(arr[x][y]!=arr[i][y])
					return false;
			}
			// 占쎌몓 獄쎻뫗占� �굜遺얜굡 (占쎈あ燁삼옙 占쎌궎�몴紐꾠걹占쎌뵠 揶쏆늿占쏙옙源뗰옙�뵠筌롳옙 6) (占쎌뇢筌잛럩�몵嚥∽옙 5甕곕뜆�럮揶쏉옙 揶쏆늿占� 占쎄퉳占쎌뵠筌롳옙 6)
			if(arr[x][y] == arr[x+2][y] || arr[x][y] == arr[x-4][y]) {
				return false; }
			System.out.println("占쎈립占쎌궎占쎌뇢 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			if(x==3) { //5揶쏆뮆彛� 占쎌넇占쎌뵥占쎈릭疫뀐옙 
				for(int i=x+1;i>x-4;i--){
					if(arr[x][y]!=arr[i][y])
						return false;
			}
			return true;
			}
			if(x==13) { //5揶쏆뮆彛� 占쎌넇占쎌뵥占쎈릭疫뀐옙 + 占쎌궎�몴紐꾠걹占쎌몵嚥∽옙 占쎌몓 占쎌넇占쎌뵥 
				for(int i=x+1;i>x-4;i--){
					if(arr[x][y]!=arr[i][y])
						return false;
			}
				if(arr[x][y] == arr[x-4][y]) {
					return false; }
			return true;
			}
			return false;
		}
	}
	//占쎈립燁삼옙 占쎌뇢筌잞옙 占쎌뵠占쎈짗 占쎌뜎 占쎌궎�몴紐꾠걹 占쎌넇占쎌뵥 (占쎄국)
	public boolean winCheckOneL(int x,int y) {
		try {			
			for(int i=x-1;i<x+4;i++){
			if(arr[x][y]!=arr[i][y])
				return false;
			}
			// 占쎌몓 獄쎻뫗占� �굜遺얜굡 (占쎈あ燁삼옙 占쎌뇢筌잛럩�뵠 揶쏆늿占쏙옙源뗰옙�뵠筌롳옙 6) (占쎌궎�몴紐꾠걹占쎌몵嚥∽옙 5甕곕뜆�럮揶쏉옙 揶쏆늿占� 占쎄퉳占쎌뵠筌롳옙 6)
			if(arr[x][y] == arr[x-2][y] || arr[x][y] == arr[x+4][y]) {
				return false; }
			System.out.println("占쎈립占쎌뇢 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			if(x==1) {
				for(int i=x-1;i<x+4;i++){
					if(arr[x][y]!=arr[i][y])
						return false;
			}
				if(arr[x][y] == arr[x+4][y]) {
					return false; }
			System.out.println("占쎈립占쎈립 占쎌뇢 ");
			return true;
			}
			if(x==11) {
				for(int i=x-1;i<x+4;i++){
					if(arr[x][y]!=arr[i][y])
						return false;
			}
			System.out.println("占쎈립占쎈립 占쎌뇢 ");
			return true;
			}
			return false;
			}
		}
	//占쎈립燁삼옙 占쎌궎�몴紐꾠걹 占쎌맄 占쏙옙揶쏄낯苑� (占쎄국)
	public boolean winCheckOneRU(int x,int y) { //x- 揶쏅Ŋ�꺖 y -筌앹빓占� 
		try {
			for(int i=x+1, j=y-1;i > x-4;i--,j++){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			//占쎌몓筌륅옙 獄쎻뫗占� �굜遺얜굡 
			if(arr[x][y] == arr[x+2][y-2] || arr[x][y] == arr[x-4][y+4]) {
				return false; }
			System.out.println("占쎌궎占쎌맄占쏙옙 占쎈립燁삼옙 ");
			return true;
			//占쎌몓筌륅옙 獄쎻뫗占� 占쎌뵥占쎈쑔占쎈뮞 占쎈퓠占쎌쑎
		} catch (ArrayIndexOutOfBoundsException e) {
			try { // 占쎌뵬占쎌뵥占쎈퓠 椰꾨챷�뒏 野껋럩�뒭 占쎈퓠占쎌쑎 占쎈퉸野껓옙 
			if((y==1&x==3)|(y==11&x==13)) {
				//占쎈퉸占쎈뼣 占쎄퐨占쎈퓠占쎄퐣 占쎈뻻占쎌삂占쎈뻻 5揶쏆뮆彛� 占쎌넇
				for(int i=x+1, j=y-1;i > x-4;i--,j++){
					if(arr[x][y]!=arr[i][j])
						return false;}
			return true;
				}
			if(x==13|y==1) {
				//占쎈퉸占쎈뼣 占쎄퐨占쎈퓠占쎄퐣 占쎈뻻占쎌삂占쎈뻻 5揶쏆뮆彛� 占쎌넇
				for(int i=x+1, j=y-1;i > x-4;i--,j++){
					if(arr[x][y]!=arr[i][j])
						return false;}
				//占쎈뼄占쎄숱揶쏆뮇�뵥筌욑옙 占쎌넇占쎌뵥
				if(arr[x][y] == arr[x-4][y+4]) { 
					System.out.println("df1");
					return false; }
			System.out.println("占쎌궎占쎌궎占쏙옙 ");
			return true;
				}
			if(y==11|x==3) {
				//占쎈퉸占쎈뼣 占쎄퐨占쎈퓠占쎄퐣 占쎈뻻占쎌삂占쎈뻻 5揶쏆뮆彛� 占쎌넇
				for(int i=x+1, j=y-1;i > x-4;i--,j++){
					if(arr[x][y]!=arr[i][j])
						return false;}
				//占쎈뼄占쎄숱揶쏆뮇�뵥筌욑옙 占쎌넇占쎌뵥
				if(arr[x][y] == arr[x+2][y-2]) { 
					System.out.println("df2");
					return false; }
			System.out.println("占쎌궎占쎌궎占쏙옙 ");
			return true;
				}
			return false;
			}
			// 占쎌뵥占쎈쑔占쎈뮞 占쎈퓠占쎌쑎 占쎈퉸野껓옙 
			catch(ArrayIndexOutOfBoundsException e1){
				return false;
			}
		}
	}
	//占쎈립燁삼옙 占쎌뇢筌잞옙 占쎌맄 占쏙옙揶쏄낯苑� (占쎄국) 
	public boolean winCheckOneLU(int x,int y) { //x-筌앹빓占� y-筌앹빓占� 
		try {
			for(int i=x-1, j=y-1;i < x+4 ;i++,j++){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			//占쎌몓筌륅옙 獄쎻뫗占� �굜遺얜굡 
			if(arr[x][y] == arr[x-2][y-2] || arr[x][y] == arr[x+4][y+4]) {
				return false; }
			System.out.println("占쎌궎占쎌맄占쏙옙 占쎈립燁삼옙 ");
			return true;
			//占쎌몓筌륅옙 獄쎻뫗占� 占쎌뵥占쎈쑔占쎈뮞 占쎈퓠占쎌쑎
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				if((y==1&x==11)|(y==11&x==1)) { //�뤃�딄퐤 占쎈あ �⑤끃�뱽 5揶쏆뮆彛� 占쎌넇占쎌뵥 
					for(int i=x-1, j=y-1 ; i < x+4 ; i++,j++){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				return true;
				}
				if(y==1||x==1) { //占쎌뇢筌잞옙 占쎈툡占쎌삋 占쎌몓 占쎌넇占쎌뵥 
					for(int i=x-1, j=y-1 ; i < x+4 ; i++,j++){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x+4][y+4]) {
					return false; }
				return true;
				}
				if(y==11|x==11) { // 占쎌궎�몴紐꾠걹 占쎌맄 占쎌넇占쎌뵥 
					for(int i=x-1, j=y-1 ; i < x+4 ; i++,j++){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x-2][y-2]) {
					return false; }
				return true;
				}
				return false;
			}
			catch(ArrayIndexOutOfBoundsException e1) {
				return false;
			}
		}
	}
	//占쎈립燁삼옙 占쎌궎�몴紐꾠걹 占쎈툡占쎌삋 占쏙옙揶쏄낯苑� (占쎄국) 
	public boolean winCheckOneRD(int x,int y) { //x- 揶쏅Ŋ�꺖  y - 揶쏅Ŋ�꺖   
		try {
			for(int i=x+1, j=y+1;i > x-4;i--,j--){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			if(arr[x][y] == arr[x+2][y+2] || arr[x][y] == arr[x-4][y-4]) {
				System.out.println("占쎌궎占쎈툡占쏙옙 占쎈립燁삼옙 ");
				return false; }
			System.out.println("占쎌궎占쎈툡占쏙옙 占쎈립燁삼옙 ");
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				if((x==3&y==13)|(x==13&y==3)) { //�뤃�딄퐤 占쎈あ �⑤끃�뱽 5揶쏆뮆彛� 占쎌넇占쎌뵥 
					for(int i=x+1, j=y+1;i > x-4;i--,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
					}
				return true;
				}
				if(x==13|y==13) { //占쏙옙揶쏄낯苑� 占쎌맄 
					for(int i=x+1, j=y+1;i > x-4;i--,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
					}
				if(arr[x][y] == arr[x-4][y-4]) {
					return false; }
				return true;
				}
				if(x==3|y==3) { //占쏙옙揶쏄낯苑� 占쎈툡占쎌삋 
					for(int i=x+1, j=y+1;i > x-4;i--,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x+2][y+2]) {
					return false; }
				return true;
				}
				return false;
				}
			catch(ArrayIndexOutOfBoundsException e1) {
				return false;
			}
	}
}
	//占쎈립燁삼옙 占쎌뇢筌잞옙 占쎈툡占쎌삋 占쏙옙揶쏄낯苑� (占쎄국)
	public boolean winCheckOneLD(int x,int y) { //
		try {
			for(int i=x-1, j=y+1; i < x+4 ;i++,j--){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			if(arr[x][y] == arr[x-2][y+2] || arr[x][y] == arr[x+4][y-4]) {
				return false; }
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				if((x==1&y==3)|(x==11&y==13)) {
					for(int i=x-1, j=y+1; i < x+4 ;i++,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				return true;
				}
				if(x==1|y==13) {
					for(int i=x-1, j=y+1; i < x+4 ;i++,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x+4][y-4]) {
					return false; }
				return true;
				}
				if(x==11|y==3) {
					for(int i=x-1, j=y+1; i < x+4 ;i++,j--){
						if(arr[x][y]!=arr[i][j])
							return false;
				}
				if(arr[x][y] == arr[x-2][y+2]) {
					return false; }
				return true;
				}
				return false;
			}
			catch(ArrayIndexOutOfBoundsException e1) {
				return false;
			}
		}
	}

	//占쎄쉽占쎄숲 占쎈씜占쎈뼄占쎌뒲 (占쎄국)
	public boolean winCheckCenterUD(int x,int y) {
		try{
			for(int i=y+2;i>y-3;i--){
				if(arr[x][y]!=arr[x][i])
					return false;
			}
			if(arr[x][y]==arr[x][y-3]||arr[x][y]==arr[x][y+3]) {
				System.out.println("A");
				return false;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			if(y==2) {
				for(int i=y+2;i>y-3;i--){
					if(arr[x][y]!=arr[x][i])
						return false;
				}
				if(arr[x][y]==arr[x][y+3]) {
					return false;
				}
				return true;
			}
			if(y==12) {
				for(int i=y+2;i>y-3;i--){
					if(arr[x][y]!=arr[x][i])
						return false;
				}
				if(arr[x][y]==arr[x][y-3]) {
					return false;
				}
				return true;
			}
			return false;
		}
		System.out.println("占쎄쉽占쎄숲 占쎈씜 ");
		return true;
	}
	//占쎄쉽占쎄숲 占쎌뵬占쎌뵠占쎈뱜 占쎌쟿占쎈늄占쎈뱜 (占쎄국)
	public boolean winCheckCenterRL(int x,int y) {
		try {
			for(int i=x-2;i<x+3;i++){
				if(arr[x][y]!=arr[i][y])
					return false;
			}
			if(arr[x][y]==arr[x-3][y]||arr[x][y]==arr[x+3][y]) {
				return false;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			if(x==2) {
				for(int i=x-2;i<x+3;i++){
					if(arr[x][y]!=arr[i][y])
						return false;
				}
				if(arr[x][y]==arr[x+3][y]) {
					return false;
				}
				return true;
			}
			if(x==12) {
				for(int i=x-2;i<x+3;i++){
					if(arr[x][y]!=arr[i][y])
						return false;
				}
				if(arr[x][y]==arr[x-3][y]) {
					return false;
				}
				return true;
			}
			return false;
		}
		System.out.println("占쎄쉽占쎄숲 占쎌뇢 ");
		return true;
	}
	//占쎄쉽占쎄숲 占쎌궎�몴紐꾠걹 占쏙옙揶쏄낯苑�
	public boolean winCheckCenterRU(int x,int y) {
		try {
			for(int i=x+2, j=y+2;i > x-3;i--,j--){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			if(arr[x][y]==arr[x+3][y+3]||arr[x][y]==arr[x-3][y-3]) {
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		System.out.println("占쎄쉽占쎄숲 占쎌궎占쏙옙 ");
		return true;
	}
	//占쎄쉽占쎄숲 占쎌뇢 占쏙옙揶쏄낯苑�
	public boolean winCheckCenterLU(int x,int y) {
		try {
			for(int i=x-2, j=y+2; i < x+3 ;i++,j--){
				if(arr[x][y]!=arr[i][j])
					return false;
			}
			if(arr[x][y]==arr[x+3][y-3]||arr[x][y]==arr[x+3][y-3]) {
				return false;}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		System.out.println("占쎄쉽占쎄숲 占쎌뇢占쏙옙 ");
		return true;
	}
	
	public int updown33(int x,int y) {
		try {
			int XY = getXY(x, y);
			if(getXY(x, y-1) ==XY && getXY(x, y-2) ==XY ) {
				return 1;
			}
			if(getXY(x, y+1) ==XY && getXY(x, y+2) ==XY ) {
				return 1;
			}
			return 0;
			}
		catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	public int rightleft33(int x,int y) {
		try {
			int XY = getXY(x, y);
			if(getXY(x-1, y) ==XY && getXY(x-2, y) ==XY ) {
				return 1;
			}
			if(getXY(x+1, y) ==XY && getXY(x+2, y) ==XY ) {
				return 1;
			}
			return 0;
			}
		catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	public int rightdiagonal33(int x,int y) {
		try {
			int XY = getXY(x, y);
			if(getXY(x+1, y-1) ==XY && getXY(x+2, y-2) ==XY ) {
				return 1;
			}
			if(getXY(x-1, y+1) ==XY && getXY(x-2, y+2) ==XY ) {
				System.out.println("A");
				return 1;
			}
			return 0;
			}
		catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	public int leftdiagonal33(int x,int y) {
		try {
			int XY = getXY(x, y);
			if(getXY(x-1, y-1) ==XY && getXY(x-2, y-2) ==XY ) {
				return 1;
			}
			if(getXY(x+1, y+1) ==XY && getXY(x+2, y+2) ==XY ) {
				return 1;
			}
			return 0;
			}
		catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	

}

