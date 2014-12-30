package engine;

import com.badlogic.gdx.Gdx;

public abstract class Values {

	/* Values class. Keeps references for some useful and important values */
	public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
	public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
	// ----------------------
	// Menu Variables
	public static float Menu_Button_Width;
	public static float Menu_Button_Height;
	public static float Inner_Button_Width;
	public static float Inner_Button_Height;
	public static float Logo_Width;
	public static float Logo_Height;
	public static float Background_Menu_Width;
	public static float Background_Menu_Height;
	// Game Variables
	public static float Background_Width;
	public static float Background_Height;
	public static float Window_Width;
	public static float Window_Height;
	public static float HUD_Bar_Image_Width;
	public static float HUD_Bar_Image_Height;
	public static float Coin_Width;
	public static float Coin_Height;
	public static float Monkey_Width;
	public static float Monkey_Height;
	public static float Monkey_Head_Width;
	public static float Monkey_Head_Height;
	public static float Trail_Width;
	public static float Trail_Height;
	public static float Snake_Width;
	public static float Snake_Height;
	public static float Snake_Cobra_Width;
	public static float Snake_Cobra_Height;
	public static float Fly_Width;
	public static float Fly_Height;
	public static float Spider_Width;
	public static float Spider_Height;
	public static float Banana_Width_Normal;
	public static float Banana_Height_Normal;
	public static float Banana_Width_Special;
	public static float Banana_Height_Special;
	public static float Pineapple_Width;
	public static float Pineapple_Height;
	public static float Watermelon_Width;
	public static float Watermelon_Height;
	public static float Strawberry_Width;
	public static float Strawberry_Height;
	public static float Grapes_Width;
	public static float Grapes_Height;
	public static float Scalar_Width;
	public static float Scalar_Height;
	public static float Entity_Scroll_Speed_Vertical;
	public static float Entity_Scroll_Speed_Horizontal;
	public static float Menu_Scroll_Speed_Horizontal;
	public static void initiate() {
		if(Values.SCREEN_HEIGHT/854 < 2)
			Scalar_Height = Values.SCREEN_HEIGHT/854;
		else
			Scalar_Height = 2f;
		if(Values.SCREEN_WIDTH/480 < 2)
			Scalar_Width = Values.SCREEN_WIDTH/480;
		else
			Scalar_Width = 2f;
		Entity_Scroll_Speed_Vertical = -340 * Scalar_Height;
		Entity_Scroll_Speed_Horizontal = -150 * Scalar_Height;
		Menu_Scroll_Speed_Horizontal = -100 * Scalar_Height;
		// Menu
		Menu_Button_Width = 270 * Scalar_Width;
		Menu_Button_Height = 110 * Scalar_Height;
		Inner_Button_Width = 120 * Values.Scalar_Width;
		Inner_Button_Height = 60 * Values.Scalar_Height;
		Window_Width = 330*Values.Scalar_Width;
		Window_Height = 300*Values.Scalar_Height;
		Logo_Width = 300 * Scalar_Width;
		Logo_Height = 150 * Scalar_Height;
		// Game
		Background_Width = Background_Menu_Width = SCREEN_WIDTH;
		Background_Height = Background_Menu_Height = SCREEN_HEIGHT;
		HUD_Bar_Image_Width = 50f * Scalar_Width;
		HUD_Bar_Image_Height = 50f * Scalar_Height;
		Coin_Width = 40 * Scalar_Width;
		Coin_Height = 40 * Scalar_Height;
		Monkey_Width = 60f * Scalar_Width;
		Monkey_Height = 70f * Scalar_Height;
		Monkey_Head_Width = 120*Scalar_Width;
		Monkey_Head_Height = 80*Scalar_Height;
		Trail_Width = 114f * Scalar_Width;
		Trail_Height = 440f * Scalar_Height;
		Snake_Width = 37f * Scalar_Width;
		Snake_Height = 100f * Scalar_Height;
		Snake_Cobra_Width = 55f * Scalar_Width;
		Snake_Cobra_Height = 120f * Scalar_Height;
		Fly_Width = 60f * Scalar_Width;
		Fly_Height = 70f * Scalar_Height;
		Spider_Width = 40f * Scalar_Width;
		Spider_Height = 50f * Scalar_Height;
		Banana_Width_Normal = 50f * Scalar_Width;
		Banana_Height_Normal = 55f * Scalar_Height;
		Banana_Width_Special = 55f * Scalar_Width;
		Banana_Height_Special = 60f * Scalar_Height;
		Pineapple_Width = 53f * Scalar_Width;
		Pineapple_Height = 70 * Scalar_Height;
		Watermelon_Width = 60 * Scalar_Width;
		Watermelon_Height = 60 * Scalar_Height;
		Grapes_Width = 53 * Scalar_Width;
		Grapes_Height = 70 * Scalar_Height;
		Strawberry_Width = 55 * Scalar_Width;
		Strawberry_Height = 60 * Scalar_Height;
	}
}