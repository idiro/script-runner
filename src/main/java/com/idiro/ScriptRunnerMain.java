/*
 *  Copyright 2016 Idiro Analytics
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.idiro;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class ScriptRunnerMain {
	
	public static void main(String[] args) throws Exception{
		try{
			if(args.length != 5){
				throw new Exception("Arguments should be:\n"+
						"1- driver\n"+
						"2- url\n"+
						"3- username\n"+
						"4- password\n"+
						"5- script");
			}
			String driver = args[0];
			String url = args[1];
			String userName = args[2];
			String password = args[3];
			String script = args[4];
			Connection mConnection = null;
			Class.forName(driver);
			mConnection = DriverManager.getConnection(url, userName, password);
			ScriptRunner runner = new ScriptRunner(mConnection, false, true);
			runner.runScript(new BufferedReader(new FileReader(script)));
			System.out.println("Close connection...");
			mConnection.close();
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
