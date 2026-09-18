# Understanding World Explorer

1. Question.kt holds the text resource ID and true/false answer for one question.
2. MainActivity creates six questions, connects the XML buttons to click listeners, and redraws the current question.
3. ExpeditionSession stores the current position and an array of responses: -1 means unanswered, 0 means false, and 1 means true. It calculates points from the responses so revisiting a question cannot add points again.
4. Answering shows a Toast and disables both answer buttons for that question. Next and Previous change the position. Restart clears the responses.
5. An explicit Intent opens CheatActivity. Extras carry the question text and correct answer to the second screen.
6. onSaveInstanceState saves quiz responses and position before rotation. onCreate restores them. CheatActivity separately saves whether its answer is revealed.
7. XML files under res/layout define the screens. strings.xml contains interface text and questions.

## Practice
Explain why a repeated answer cannot increase the score. Find the Intent extras in both Activities. Answer two questions, rotate the device, and check the progress. Reveal an answer, rotate, and confirm it stays revealed. Answer all six, check the result, then restart.
