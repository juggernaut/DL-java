## DL-java


A java implementation of the Duckworth-Lewis method of calculating target scores in limited overs cricket matches. The Standard Edition tables are used. Note that the Professional Edition is not freely available. The Standard Edition tables can be used for first-class and lower levels of the game.


### Examples

    final InningsBuilder builder = InningsBuilder.forAllottedOvers(50);
    final Innings sriLanka = builder.ended(atScore(runs(268), down(9))).build();
    final InningsBuilder builder2 = InningsBuilder.forAllottedOvers(50);
    final Innings southAfrica = builder2.interrupted(atScore(runs(229), down(6)), in(45), lost(5)).build();
    final DLCalculator calculator = new DLCalculator(sriLanka, southAfrica);
    calculator.getRevisedTarget();