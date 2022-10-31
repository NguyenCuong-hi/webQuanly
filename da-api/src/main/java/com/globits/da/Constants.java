package com.globits.da;

public class Constants {
	public static enum StaffType {
		Sale(1), // nhân viên bán hàng
		Cashier(2), // nhân viên thu ngân
		Other(3)// khác
		;

		private Integer value;

		private StaffType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public static enum ChannelAds {// kenh quang cao
		Webiste(1), // website
		Contextual_Advertiser(2), // khen hquang cao
		Social_Netword(3), // mang xa hoi
		Youtube_channel(4)// youtube
		;

		private Integer value;

		private ChannelAds(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}
	
	public static enum Social_Netword {// kenh quang cao
		Facebook(1), // website
		Zalo(2), // khen hquang cao
		Tiktok(3), // mang xa hoi
		Other(4)// youtube
		;

		private Integer value;

		private Social_Netword(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}


	public static final String PATTERN_EMPLOYEE_CODE= "^[0-9\\w-!@#$%^&*]{6,10}$";
	public static final String PATTERN_EMAIL= "[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	public static final String PATTERN_PHONE ="^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";

	// Column in excel

	public static final int COLUMN_INDEX_ID = 0;
	public static final int COLUMN_INDEX_NAME = 1;
	public static final int COLUMN_INDEX_AGE = 2;
	public static final int COLUMN_INDEX_CODE = 3;
	public static final int COLUMN_INDEX_EMAIL = 4;

	public static final int COLUMN_INDEX_PHONE = 5;
	public static final int COLUMN_INDEX_PROVINCE = 6;
	public static final int COLUMN_INDEX_DISTRICT = 7;
	public static final int COLUMN_INDEX_COMMUNE = 8;

}
