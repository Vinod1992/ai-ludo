package de.uni_mannheim.informatik.ai.ludo.model;

import de.uni_mannheim.informatik.ai.ludo.intent.PlayerIntent;

/**
 * This Player interface is the common interface all concrete players have to implement.
 *
 * @author gtrefs
 */
public interface Player {

    /**
     * Sets the color of this player.
     * @param color
     */
    public void setColor(Game.Color color);

    /**
     * Returns the color of this player.
     * @return the color of this player
     */
    public Game.Color getColor();

    /**
     * Sets the current player pawns of the player.
     * It is called during the game initialization.
     * @param pawns pawns belonging to the player
     */
    public void setPawns(Pawn[] pawns);

    /**
     * Rreturns the pawns of this player.
     * @return the pawns of this player.
     */
    public Pawn[] getPawns();

    /**
     * Returns a specific pawns identified throigh the index parameter.
     * @param index the index of the pawn to return
     * @return the pawn identified by the index or null
     */
    public Pawn getPawn(int index);

    /**
     * Sets the player path.
     * @param path the path of this player
     * @see de.uni_mannheim.informatik.ai.ludo.model.Path
     */
    public void setPath(Path path);
    
    /**
     * Returns the path of this player.
     * @return the current path of this player
     * @see de.uni_mannheim.informatik.ai.ludo.model.Path
     */
    public Path getPath();

    /**
     * Called by the game if this player should move one of its pawns.
     * The game adavances to the next game state if this mehtod dispatches a {@link de.uni_mannheim.informatik.ai.ludo.intent.MoveIntent MoveIntent}.
     * If the intent has been successfull the {@link #successIntent successIntent} method is called, otherwise it is rejected and the {@link #rejectIntent rejectIntent} method is called.
     * However, eventually this method has to dispatch a MoveIntent otherwise the game will get stucked.
     */
    public void movePawn();

    /**
     * Called by the game if this player should roll the dice.
     * The game adavances to the next game state if this mehtod dispatches a {@link de.uni_mannheim.informatik.ai.ludo.intent.RollDiceIntent RollDiceIntent}.
     * If the intent has been successfull the {@link #successIntent successIntent} method is called, otherwise it is rejected and the {@link #rejectIntent rejectIntent} method is called.
     * However, eventually this method has to dispatch a RollDiceIntent otherwise the game will get stucked.
     */
    public void rollTheDice();

    /**
     * Called if the players recent intent has been rejected.
     * @param intent the intent which has been rejected by the game.
     * @see de.uni_mannheim.informatik.ai.ludo.intent.PlayerIntent
     */
    public void rejectIntent(PlayerIntent intent);

    /**
     * Called if the players recent intent has been successful.
     * Only successfull intents can cause the game to move on.
     * Further, if a successfull intent causes a throw of another player's pawn or leads to a game win, the methods {@link #enemyPawnThrownByIntent} or {@link #gameWonByIntent} are called before the this method.
     * However, eventually this method is called in both cases.
     * @param intent the intent which has been rejected by the game.
     * @see de.uni_mannheim.informatik.ai.ludo.intent.PlayerIntent
     */
    public void successIntent(PlayerIntent intent);

    /**
     * Called after a {@link de.uni_mannheim.informatik.ai.ludo.intent.PlayerIntent PlayerIntent} lead to a throw of another players pawn.
     * @param enemyPawn the pawn which has been thrown
     * @param intent the player intent which lead to this throw
     */
    public void enemyPawnThrownByIntent(Pawn enemyPawn,PlayerIntent intent);

    /**
     * Called after a {@link de.uni_mannheim.informatik.ai.ludo.intent.PlayerIntent PlayerIntent} lead to a win.
     * @param intent the player intent which lead to the win
     */
    public void gameWonByIntent(PlayerIntent intent);
}