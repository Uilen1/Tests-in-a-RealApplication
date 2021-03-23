package model.utilities;

import java.awt.Toolkit;

import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.WinDef;

public class Scale {
	
	public static double getScreenScale() throws Exception {
//		WinDef.HDC hdc = GDI32.INSTANCE.CreateCompatibleDC(null);
//		if (hdc != null) {
//			float actual = GDI32.INSTANCE.GetDeviceCaps(hdc, 10 /* VERTRES */);
//			float logical = GDI32.INSTANCE.GetDeviceCaps(hdc, 117 /* DESKTOPVERTRES */);
//			GDI32.INSTANCE.DeleteDC(hdc);
//			if (logical != 0 && logical / actual >= 1) {
//				return logical / actual;
//			}
//		}
		//escala usa 96 como referencia para tela de 100%
		return (Toolkit.getDefaultToolkit().getScreenResolution() / 96.0f);
	}

}
