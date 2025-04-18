# Java Chess Game (Refactored)

This is a refactored version of a classic Swing-based Java chess game. The codebase has been restructured into MVC architecture with clear separation of concerns, unit testing coverage, and extended features.

## Project Structure

- **Model**: Contains core game logic (`Piece`, `Pawn`, `Square`, etc.)
- **View**: Swing-based GUI components (`Board`, `GameWindow`, `StartMenu`, etc.)
- **Controller**: Coordinates game state (`GameController`, `MoveService`, `CheckmateDetector`, etc.)

## Key Features

- Full chess rules including:
  - Piece-specific movement
  - Pawn promotion
  - Castling
  - En passant
  - Check, checkmate, and stalemate detection
- Drag-and-drop GUI
- Timer support
- MVC refactoring with testability in mind

## Unit Tests

Includes comprehensive unit tests:
- Piece movement validation (Pawn, Rook, Knight, Bishop, Queen, King)
- Special rules: castling, en passant, pawn promotion
- Game states: check, checkmate, stalemate

## How to Run

1. Open the project in IntelliJ or another Java IDE.
2. Make sure your resources (e.g. piece images like `wp.png`, `bp.png`) are in the correct path.
3. Run the `Game` class to launch the game.

## How to Run Tests

Tests are written using JUnit 5.

- To run all tests: Right-click on the `test` directory and select **Run Tests**.
- Or run specific test classes like `PawnTest`, `KingTest`, `KingCastlingTest`, etc.

## Dependencies

- Java 17 or above
- JUnit 5 for testing
