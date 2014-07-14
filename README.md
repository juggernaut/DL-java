## DL-java


A java implementation of the Duckworth-Lewis method of calculating target scores in limited overs cricket matches. The implementation uses Standard Edition resource tables. Note that the Professional Edition resource tables are not freely available. According to ICC guidelines, the Standard Edition tables can be used for first-class and lower levels of the game.

The library exposes a fluent API that makes it easy to express cricketing nomenclature in a typesafe and natural way. The best way to get started is with the Examples section.


### Examples

In the (in)famous tied match in the 2003 World Cup, Mark Boucher defended the last ball before rain stopped play, thinking that the Duckworth-Lewis par score represented a winning target. Sri Lanka scored 268/9 in the allotted 50 overs and South Africa were 229/6 in 45 overs when rain stopped play. Hence a total of 5 overs were lost in the interruption. It's super easy to calculate the target for South Africa at the end of 45 overs using DL-java:

    final InningsBuilder builder = InningsBuilder.forAllottedOvers(50);
    final Innings sriLanka = builder.ended(atScore(runs(268), down(9))).build();
    final InningsBuilder builder2 = InningsBuilder.forAllottedOvers(50);
    final Innings southAfrica = builder2.interrupted(atScore(runs(229), down(6)), in(45), lost(5)).build();
    final DLCalculator calculator = new DLCalculator(sriLanka, southAfrica);
    System.out.println(calculator.getRevisedTarget()); //prints 230
    
The code is fairly self-explanatory. An `Innings` forms the basis for expressing the game situation. A builder object is used to construct the innings. An innings has a fixed number of allotted overs, any number of `Interruption`s and an optional final score. If the final score isn't specified, the score at the last interruption is taken as the final score. An interruption is a stoppage of play due to rain or other unscheduled events. An interruption is specified by the score when play was interrupted and the number of overs lost due to the interrupting event. Finally, the `DLCalculator` object is used to calculate the revised target for the side batting second. Note that the revised target is the actual winning score, *not* the par score. Dare I say that if the coach Eric Simons was using DL-java, we might well have seen South Africa win their first World Cup at home :)