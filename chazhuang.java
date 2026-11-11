/*1、微信程序中可以插装青桔等小程序。某公司编写了一款名为“菜场”的软件，计划支持插装小程序，
 * 插装标准为CaiChangApplet，符合该标准的小程序都可插装到菜场中。
 * 该标准内有start()、run()、stop()三项功能。
 * main中演示了插装“白菜”、“萝卜”等小程序，以及插装后的运行结果。
 * 要求，菜场中至多能插装10个小程序。为简化处理，添加小程序时，可不考虑小程序插满的情形。
 * 请完成CaiChangApplet标准的设计，以及菜场（CaiChang）、白菜（BaiCai）、萝卜（LuoBo）等类的设计。
class App1{
    public static void main (String[] args) {
        CaiChang c=new CaiChang();
        BaiCai bc=new BaiCai();
        LuoBo lb=new LuoBo();
        c.add(bc); c.add(lb);
        c.run();
    }
}
【运行结果为：】
白菜启动    白菜运行    白菜停止
萝卜启动    萝卜运行    萝卜停止*/
import java.util.Scanner;
interface CaiChangApplet{//caichangapplet
	void start();
	void run();
	void stop();
}
class BaiCai implements CaiChangApplet{
	private String name="白菜";
	public void start() {
		System.out.print(name+"启动	");
	}
	public void run() {
		System.out.print(name+"运行	");
	}
	public void stop() {
		System.out.print(name+"停止	");
	}
}
class LuoBo implements CaiChangApplet{
	private String name="萝卜";
	public void start() {
		System.out.print(name+"启动	");
	}
	public void run() {
		System.out.print(name+"运行	");
	}
	public void stop() {
		System.out.print(name+"停止	");
	}
}
class TuDou implements CaiChangApplet{
	private String name="土豆";
	public void start() {
		System.out.print(name+"启动	");
	}
	public void run() {
		System.out.print(name+"运行	");
	}
	public void stop() {
		System.out.print(name+"停止	");
	}
}class tomato implements CaiChangApplet{
	private String name="番茄";
	public void start() {
		System.out.print(name+"启动	");
	}
	public void run() {
		System.out.print(name+"运行	");
	}
	public void stop() {
		System.out.print(name+"停止	");
	}
}
class CaiChang{
	CaiChangApplet[] a=new CaiChangApplet[10];
	public void add(CaiChangApplet p) {
		for(int i=0;i<a.length;i++) {
			if(a[i]==null) {
				a[i]=p;
				break;
			}
		}
	}
	public void run() {
		for(int i=0;i<a.length;i++) {
			if(a[i]!=null) {
				a[i].start();
				a[i].run();
				a[i].stop();
				System.out.print("\n");
			}
		}
		
	}
}

class App1{
    public static void main (String[] args) {
        CaiChang c=new CaiChang();
        BaiCai bc=new BaiCai();
        LuoBo lb=new LuoBo();
        c.add(bc); c.add(lb);
        c.run();
    }
}