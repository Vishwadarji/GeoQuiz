# World Explorer

A geography quiz for Android, built with Kotlin and XML layouts. Open this folder in Android Studio, let Gradle sync, select an emulator or Android device, and press Run.

## Features
Six true/false questions, correct/incorrect Toasts, next/previous navigation, score and final result, restart, and a separate answer Activity launched with an explicit Intent and question/answer extras. Answers and position survive rotation. Each question can score only once. Viewing a hint does not penalize the score.

## Verification
Debug build and Android lint passed with zero errors. Eight notices suggest newer dependency or build-tool versions. On the API 35 emulator, checked all six questions, correct-answer Toast, an intentionally incorrect answer, running score, final score of 5/6, answer guide and return, saved question/answers/score across rotation, and restart to 0/6.

## Practice checks
- Answer one correctly and one incorrectly; check Toasts and score.
- Revisit answered questions; confirm answers are disabled and score is unchanged.
- Answer all six; check the final score.
- Rotate midway; check position, answers and score are retained.
- Open Cheat, reveal, rotate, return; confirm both screens retain state.
- Restart; check score and all answers reset.

Upload source, resources, Gradle files and wrapper to GitHub. Do not upload local.properties, .idea, .gradle or build folders. Submit your repository URL only after testing; the assignment excludes changes after submission.
