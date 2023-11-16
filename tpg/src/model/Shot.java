package model;

public class Shot {
	public String shot;
	public String save;

	public Shot() {
		shot = null;
		save = null;
	}
	
	public String getShot() {
		return shot;
	}

	public void setShot(String shot) {
		this.shot = shot;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}
	
	public boolean getShotResult() {
		char[] temp = save.toCharArray();
		char saveRow = temp[0];
		int saveColumn = Integer.parseInt(String.valueOf(temp[1]));
		
		if(save.equals(shot))
			return false; //no goal
		else {
			switch(String.valueOf(saveRow)) {
				case "A":
					switch(saveColumn) {
						case 1:
						case 2:
							if(shot.equals("B2"))
								return false;
							else
								return true;
						case 3:
						case 4:
							if(shot.equals("B3"))
								return false;
							else
								return true;
					}
				case "B":
					switch(saveColumn) {
						case 1:
							if(shot.equals("B2"))
								return false;
							else
								return true;
						case 2:
							if(shot.equals("C2"))
								return false;
							else
								return true;
						case 3:
							if(shot.equals("C3"))
								return false;
							else
								return true;
						case 4:
							if(shot.equals("B3"))
								return false;
							else
								return true;
					}
				case "C":
					switch(saveColumn) {
						case 1:
							if(shot.equals("C2"))
								return false;
							else
								return true;
						case 2:
							if(shot.equals("C3"))
								return false;
							else
								return true;
						case 3:
							if(shot.equals("C2"))
								return false;
							else
								return true;
						case 4:
							if(shot.equals("C3"))
								return false;
							else
								return true;
					}
				default:
					//TODO: Might need an exception here
					return false;
			}
		}
	}

}
