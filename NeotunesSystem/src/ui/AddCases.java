package ui;
 public class AddCases{

 	String[] add;
 	public AddCases(){
 		add = new String[10];
 		add[0] = nikeAdd();
 		add[1] = cocaColaAdd();
 		add[2] = mmAdd();
 	}
 	public String[] getAdd(){
 		return add;
 	}
 	public String nikeAdd(){
 		String msj = ""+
 		"'''''''''''''''''''''''''''''''\n"+
 		"' ADD                         '\n"+
 		"'     _              ___---.  '\n"+
 		"'    : :         __--___---'  '\n"+
 		"'   |  |    __--__--          '\n"+
 		"'   '_  -_--__--              '\n"+
 		"'     --__--                  '\n"+
 		"'                             '\n"+
 		"'         JUST DO IT          '\n"+
 		"'                             '\n"+
 		"'''''''''''''''''''''''''''''''";
 		return msj;
 	}
 	public String cocaColaAdd(){
 		String msj = ""+
 		"'''''''''''''''''''''''''''''''\n"+
 		"' ADD  ___                    '\n"+
 		"'     {___}                   '\n"+
 		"'      |||                    '\n"+
 		"'     ]   [                   '\n"+
 		"'    |_____|                  '\n"+
 		"'    |_____|  OPEN HAPPINESS  '\n"+
 		"'    |     |                  '\n"+
 		"'    |     |                  '\n"+
 		"'   |       |                 '\n"+
 		"'   '-_____-'                 '\n"+
 		"'''''''''''''''''''''''''''''''";
 		return msj;
 	}
 	public String mmAdd(){
 		String msj = ""+
 		"'''''''''''''''''''''''''''''''\n"+
 		"' ADD      _--```--_          '\n"+
 		"'        .'         '.        '\n"+
 		"'       :             :       '\n"+
 		"'      [     m & m     ]      '\n"+
 		"'       :             :       '\n"+
 		"'        '-_       _-'        '\n"+
 		"'           ''---''           '\n"+
 		"'                             '\n"+
 		"'     Melts in your mouth     '\n"+
 		"'       Not in your hand      '\n"+
 		"'''''''''''''''''''''''''''''''";
 		return msj;
 	}
 }