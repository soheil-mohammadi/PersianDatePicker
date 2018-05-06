# PersianDatePicker
An material android library  for Persian date picker :)
It works for api +14 ;) 

<img src="https://github.com/soheil-mohammadi/PersianDatePicker/blob/master/intro_lib.gif" width="40%"/> 

# Version 2.0 released!

In this version we added beauty animations and improve codes . enjoy it :) 


# HOW TO USE THIS LIBRARY ?!
Add the dependency :
```gradle
	dependencies {
	       compile 'com.soheil-mohammadi:PersianDatePickerModule:2.0.4'
	}

```
This library has two options ! 

# Option 1 :
It can used by showing a material dialog with animations for detecting persian date picker ! ==> Dialog Date Picker


# Option 2 :
It can used by showing a material custom view that can for paging dates for detecting persian date picker !  ==> View Date Picker

# Dialog Date Picker :

<img src="https://github.com/soheil-mohammadi/PersianDatePicker/blob/master/DialogDatePicker.png" width="50%" />
In the xml layout  file :

```xml
<LinearLayout android:layout_width="match_parent"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_height="match_parent"
    tools:context="com.picker.date.persian.parisa.soheil.persiandatepicker.MainActivity"
    xmlns:android="http://schemas.android.com/apk/res/android">


    <TextView
        android:id="@+id/txt_show_dialog"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        style="@style/TextAppearance.Design.Hint"
        android:text="show me dialog !"
        android:gravity="center"/>


</LinearLayout>

```

In the MainActivity.java file :


```java
public class MainActivity extends AppCompatActivity implements  DayClickListenerPersianDatePicker{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         TextView txt_show_dialog =  findViewById(R.id.txt_show_dialog);
         txt_show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DatePickerPersian datePickerPersian = DatePickerPersian.get_instance();
                datePickerPersian.show_Dialog(this ,DatePickerPersian.TRANSALATE_ANIM , null  , 0 ,R.color.colorPrimary, this);
            }
        });
    }

    @Override
    public void on_click(int year, int month, int day) {
        Toast.makeText(this, year + "/" + month +"/" + day , Toast.LENGTH_SHORT).show();
    }
}

```


In the above we first define a simple TextView for showing dialog date picker with onClickListener . so in the MainActivity.java in onclick event of TextView  we init dialog date picker then call show_dialog method for this !



<b>show_dialog method </b>  :

<b>param 1</b> : this needs an context . <br>
<b>param 2</b> : it need an style for dialog . you can use predefine style in date picker persian :) . so you will have two options : 1- DatePickerPersian.TRANSALATE_ANIM  --  2- DatePickerPersian.ROTATE_ANIM
This styles have beauty animtions for your dialog :)  <br>
<b>param 3</b>: it needs a Typeface font for customing dialog font . if you set it to null it will use default typeface :)  <br>
<b>param 4</b>: it needs an custom color for header background of your dialog . if you set it to zero it will use default color :)  <br>
<b>param 5</b> : it needs an custom color for month texts background of your dialog . if you set it to zero it will use default color :)  <br>
<b>param 6</b>: it needs a listener for when user detect a specific date . we do it with implemention of activity from DayClickListenerPersianDatePicker then pass this activity to method ! you must implement on_click method . this pass year , month and day of that user selected it !  <br>
  
  
  # View Date Picker :
  
  <img src="https://github.com/soheil-mohammadi/PersianDatePicker/blob/master/ViewDatePicker.png" width="50%" />
In the xml layout  file :

```xml
<LinearLayout android:layout_width="match_parent"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/linear"
    android:orientation="vertical"
    android:layout_height="wrap_content"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context="com.picker.date.persian.parisa.soheil.persiandatepicker.MainActivity"
    xmlns:android="http://schemas.android.com/apk/res/android">


    <com.picker.date.persian.parisa.soheil.persiandatepicker.DatePickerPersianView
        android:id="@+id/date_picker_persian"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</LinearLayout>

```

In the MainActivity.java file :


```java
public class MainActivity extends AppCompatActivity implements  DayClickListenerPersianDatePicker{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePickerPersianView date_picker_persian = (DatePickerPersianView) findViewById(R.id.date_picker_persian);
        date_picker_persian.set_support_fragmentManager(getSupportFragmentManager());
    }


    @Override
    public void on_click(int year, int month, int day) {
        Toast.makeText(this, year + "/" + month +"/" + day , Toast.LENGTH_SHORT).show();
    }
}

```


In the above we define a  DatePickerPersianView  for showing  View date picker in xml layout file  . so in the MainActivity.java in onCreate method we init  it then call set_support_fragmentManager method . it needs a FragmentManager!
Then we needs a listener for when user detect a specific date . we do it with implemention of activity from DayClickListenerPersianDatePicker! this pass year , month and day of that user selected it !  <br>

-------------------------------------------------------------------------------------
##Contact :
You can send your comments for improve this library to me ;) 
Email : mad4r20@gmail.com </br>
Telegram : <a href="https://t.me/p_soheil_mohammadi_p">Soheil Mohammadi</a> </br>

-------------------------------------------------------------------------------------
Good Luck :)
