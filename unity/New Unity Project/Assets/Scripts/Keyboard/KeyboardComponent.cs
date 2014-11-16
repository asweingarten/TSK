using UnityEngine;
using System.Collections.Generic;
using System;


public class KeyboardComponent : MonoBehaviour {
	
	private IList<KeyboardRowComponent> rows = new List<KeyboardRowComponent>();
	private int rowCount;
	
	public KeyboardComponent(){
		this.rowCount = 0;
	}
	
	public void addRow(KeyboardRowComponent row){
		this.rows.Add(row);
		this.rowCount++;
	}
	
}