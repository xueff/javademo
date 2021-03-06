package jna;

import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;

/**
 * 使用winID来获得窗口的类型和标题，然后发送消息或者其他操作
 *
 */
public class JnaWrite {

    public static void main(String[] args) {
        write(JnaBaseInfo.getWindowByTitle("Postman"),"input");
    }



/**
 * Created by lenovo on 2017/4/27.
 * 使用winID来获得窗口的类型和标题，然后发送消息或者其他操作（鼠标已聚焦）
 *
 */

    public static void write(HWND hwnd,String inputStr) {
        User32.INSTANCE.ShowWindow(hwnd, 9 );
        User32.INSTANCE.SetForegroundWindow(hwnd);

        WinUser.INPUT input = new WinUser.INPUT();
        for(Character c: inputStr.toCharArray()) {
            sendChar(input,c);
        }
    }

    static void  sendChar(WinUser.INPUT input,char ch){

        input.type = new WinDef.DWORD( WinUser.INPUT.INPUT_KEYBOARD );
        input.input.setType("ki"); // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        input.input.ki.wScan = new WinDef.WORD( 0 );
        input.input.ki.time = new WinDef.DWORD( 0 );
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR( 0 );
        // Press
        input.input.ki.wVk = new WinDef.WORD( Character.toUpperCase(ch) ); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD( 0 );  // keydown

        User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );

        // Release
        input.input.ki.wVk = new WinDef.WORD( Character.toUpperCase(ch) ); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD( 2 );  // keyup

        User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );

    }
}
