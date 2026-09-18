package com.vishwa.geoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val questions = listOf(
        Question(R.string.q0, true),
        Question(R.string.q1, false),
        Question(R.string.q2, true),
        Question(R.string.q3, false),
        Question(R.string.q4, true),
        Question(R.string.q5, false)
    )
    private val session = ExpeditionSession(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        session.position = savedInstanceState?.getInt("session.position") ?: 0
        session.responses = savedInstanceState?.getIntArray("session.responses") ?: session.responses
        findViewById<Button>(R.id.true_button).setOnClickListener { answer(true) }
        findViewById<Button>(R.id.false_button).setOnClickListener { answer(false) }
        findViewById<Button>(R.id.next_button).setOnClickListener {
            session.position = (session.position + 1) % questions.size
            render()
        }
        findViewById<Button>(R.id.previous_button).setOnClickListener {
            session.position = (session.position + questions.size - 1) % questions.size
            render()
        }
        findViewById<Button>(R.id.cheat_button).setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra(CheatActivity.QUESTION, getString(questions[session.position].textId))
            intent.putExtra(CheatActivity.ANSWER, questions[session.position].answer)
            startActivity(intent)
        }
        findViewById<Button>(R.id.restart_button).setOnClickListener {
            session.restart()
            render()
        }
        render()
    }

    private fun answer(choice: Boolean) {
        if (session.responses[session.position] != -1) return
        session.responses[session.position] = if (choice) 1 else 0
        Toast.makeText(this, if (choice == questions[session.position].answer) R.string.correct else R.string.incorrect, Toast.LENGTH_SHORT).show()
        render()
    }

    private fun label(value: Boolean) = getString(if (value) R.string.true_label else R.string.false_label)

    private fun render() {
        val answered = session.completed
        val score = session.points
        findViewById<TextView>(R.id.question_text).setText(questions[session.position].textId)
        findViewById<TextView>(R.id.progress).text = getString(R.string.progress, session.position + 1, questions.size)
        findViewById<TextView>(R.id.score).text = if (answered == questions.size)
            getString(R.string.final_score, score, questions.size)
        else getString(R.string.score, score, questions.size, answered)
        val available = session.responses[session.position] == -1
        findViewById<Button>(R.id.true_button).isEnabled = available
        findViewById<Button>(R.id.false_button).isEnabled = available
        findViewById<TextView>(R.id.feedback).text = if (available) getString(R.string.unanswered)
            else getString(R.string.answered, label(session.responses[session.position] == 1), label(questions[session.position].answer))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("session.position", session.position)
        outState.putIntArray("session.responses", session.responses)
        super.onSaveInstanceState(outState)
    }
}
