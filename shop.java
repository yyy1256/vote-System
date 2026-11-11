/*购物车设计：某电商平台，为方便商品展示，需要将商品分类。
 * 在购物时，可将不同类别的商品加入购物车，并自动计价。
 * 假设购买如下商品：U盘2个（每只100元）；显示器1个（2000元）；音箱一对（300元）。
 * 请设计购物车，能添加、移除商品，并自动计算出总价格。
 * （提示：购物车内有商品数组，以容纳不同种类的商品）*/
 class shop {
	 private String name;
	 private int price;
	 public void setprice(int x){
		 price=x;
	 }
	 public int showp() {
		 return price;
	 }
	 public void show() {
		 
	 }
	 public void setname(String f) {
		 name=f;
	 }
	 public String n() {
		 return name;
	 }
}
 class upan extends shop{
	 upan(){
		 setprice(100);
		 setname("u盘");
	 }
	 public void show() {
		 System.out.print("u盘：100");
	 }
 }
 class xsq extends shop{
	 xsq(){
		 setname("显示器");
		 setprice(2000);
	 }
	 public void show() {
		 System.out.print("显示器：2000");
	 }
 }
 class yx extends shop{
	 yx(){
		 setname("音响");
		 setprice(300);
	 }
	 public void show() {
		 System.out.print("音响：300");
	 }
 }
 class car{
	 shop[] s=new shop[50];
	 car() {
		 s[0]=new yx();
		 s[1]=new upan();
		 s[2]=new xsq();
		 s[3]=new xsq();
	 }
	 public void add(shop x) {
		 for(int i=0;i<s.length;i++) {
			 if(s[i]==null) {
				 s[i]=x;
				 break;
			 }
		 }
	 }
	 public void delet(String t) {
		 for(int i=0;i<s.length;i++) {
			 if(t.equals(s[i].n())) {
				 for(int j=i;j<s.length-1;j++) {
					 s[j]=s[j+1];
				 }
				 s[s.length-1]=null;
				 break;
			 }
		 }
	 }
	 public int pay() {
		 int sum=0;
		 System.out.print("购买物品：\n");
		 for(int i=0;i<s.length;i++) {
			 if(s[i]!=null) {
				 sum=sum+s[i].showp();
				 s[i].show();
				 System.out.print("\n");
			 }
		 }
		 return sum;
	 }
 }
class app2{
	public static void main(String[] o) {
		car d=new car();
		yx j=new yx();
		d.add(j);
		xsq p=new xsq();
		d.delet(p.n());
		System.out.print("需付款："+d.pay());
}
}