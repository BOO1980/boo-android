package boo.kitchendroid;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private Button[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initQuestionScreen();
    }

    /**
     * Looks in the Question string and find the name of the array that holds the questions
     * @param res
     * @param index
     * @return
     */
    private static int getQuestionID(Resources res, int index){
        //Looks up the string array
        String[] questions = res.getStringArray(R.array.questions);
        return res.getIdentifier(questions[index],"array", "boo.kitchendroid");

    }

    private int getQuestionIndex(){
        return getIntent().getIntExtra("KitchenDroid.Question", 0);
    }

    private void initQuestionScreen(){

        Resources resources = getResources();
        //Layouts
        TextView question = (TextView) findViewById(R.id.question);
        ViewGroup answer = (ViewGroup) findViewById(R.id.answers);

        int questionID = getQuestionID(resources, getQuestionIndex());
        String[] questionData = resources.getStringArray(questionID);
        question.setText(questionData[0]);

        int answerCount = questionData.length - 1;
        buttons = new Button[answerCount];
        for (int i = 0; i < answerCount; i++){
            String answers = questionData[i + 1];
            Button button = new Button (this);
            button.setText(answers);
            buttons[i] = button;
            answer.addView(button);
        }
    }
}
