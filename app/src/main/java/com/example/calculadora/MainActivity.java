package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    TextView disp, disp2;
    String aux;
    Button bpres;
    Double memoria;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


    }


    public void acercade(View view) {
        Intent i = new Intent(this, Acerca.class );
        startActivity(i);
    }

    public void bot0(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"0");
    }

    public void bot1(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"1");
    }

    public void bot2(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"2");
    }

    public void bot3(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"3");
    }

    public void bot4(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"4");
    }

    public void bot5(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"5");
    }

    public void bot6(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"6");
    }

    public void bot7(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"7");
    }

    public void bot8(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"8");
    }

    public void bot9(View view){
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText()+"9");
    }

    public void botpunt(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+".");
        bpres.setEnabled(false);
    }

    public void suma(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"+");
        bpres.setEnabled(true);
    }

    public void resta(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"-");
        bpres.setEnabled(true);
    }

    public void multi(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"*");
        bpres.setEnabled(true);
    }

    public void divi(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"/");
        bpres.setEnabled(true);
    }

    public void limp(View view){
        bpres = findViewById(R.id.bp);
        disp = findViewById(R.id.disp);
        disp.setText("");
        bpres.setEnabled(true);
    }

    public void borra(View view) {
        disp = findViewById(R.id.disp);
        if(disp.length()> 0)
        {
            aux = disp.getText().toString();
            aux = aux.substring(0,aux.length()-1);
            disp.setText(aux);
        }
        else
            {
                disp.setText(" ");
            }

    }

    public void igual(View view) {
        disp = findViewById(R.id.disp);
        if (disp.length()>0)
        {
            aux = disp.getText().toString();
            disp.setText(eval(aux)+"");
        }
        else
            {
                disp.setText("");
            }

    }


    public static double eval(final String str) {

        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void parab(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"(");
        bpres.setEnabled(true);
    }

    public void parcer(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+")");
        bpres.setEnabled(true);
    }

    public void sen(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"sin(");
        bpres.setEnabled(true);
    }

    public void cos(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"cos(");
        bpres.setEnabled(true);
    }

    public void tan(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"tan(");
        bpres.setEnabled(true);
    }

    public void sqrt(View view){
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText()+"sqrt(");
        bpres.setEnabled(true);
    }

    public void porcen(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "%");
        bpres.setEnabled(true);
    }

    public void expo(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "^");
        bpres.setEnabled(true);
    }

    public void inv(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText("1/"+disp.getText());
        bpres.setEnabled(true);
    }

    public void pi(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "π");
        bpres.setEnabled(true);
    }

    public void memag(View view){
        disp = findViewById(R.id.disp);
        disp2 = findViewById(R.id.disp2);
        if(disp.length() == 0)
        {
            disp2.setText("");
        }
        else
            {
                memoria = Double.parseDouble(disp.getText()+"");
                disp2.setText(String.valueOf(memoria));
            }
    }

}
