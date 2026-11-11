package service.uncoes;


public class Derivador{
    public Node derivar(Node f){ 
        if(f==null){
            return null;
        }
        switch(f.getToken()){
            case "+":
                return new Node("+", derivar(f.getEsquerda()), derivar(f.getDireita()));
            case "-":
                return new Node("-", derivar(f.getEsquerda()), derivar(f.getDireita()));
            case "*":
                return new Node("+",
                                new Node("*",derivar(f.getEsquerda()),f.getDireita()),
                                new Node("*",f.getEsquerda(),derivar(f.getDireita()))
                );
            case "/":
                return new Node("/",
                                new Node("-",
                                    new Node("*",derivar(f.getEsquerda()),f.getDireita()),
                                    new Node("*",f.getEsquerda(),derivar(f.getDireita()))
                                ),
                                new Node("*",f.getDireita(),f.getDireita())
                );
            case "^":
                switch(f.getEsquerda().getToken()){
                    case "x":
                        return new Node("*",
                                        f.getDireita(),
                                        new Node("^",
                                            f.getEsquerda(),
                                            new Node("-",
                                                f.getDireita(),
                                                new Node("1",null,null)
                                            )
                                        )
                        );
                    default:
                        return new Node("*",
                                        new Node("^",
                                                f.getEsquerda(),
                                                f.getDireita()
                                        ),
                                        new Node("+",
                                            new Node("/",
                                                new Node("*",
                                                    derivar(f.getEsquerda()),
                                                    f.getDireita()
                                                ),
                                                f.getEsquerda()
                                            ),
                                            new Node("*",
                                                new Node("ln",
                                                    null,
                                                    f.getEsquerda()
                                                ),
                                                derivar(f.getDireita())
                                            )
                                        )
                        );
                }
            case "sen":
                return new Node("*",
                    derivar(f.getDireita()),
                    new Node("cos",null,f.getDireita())
                );
            case "cos":
                return new Node("*",
                    derivar(f.getDireita()),
                    new Node("*",new Node("-1",null,null),new Node("sen",null,f.getDireita()))
                );
            case "tan":
                return new Node("*",
                    derivar(f.getDireita()),
                    new Node("^",new Node("sec",null,f.getDireita()),new Node("2",null,null))
                );
            case "sec":
                return new Node("*",
                    derivar(f.getDireita()),
                    new Node("*",new Node("sec",null,f.getDireita()),new Node("tan",null,f.getDireita()))
                );
            case "cossec":
                return new Node("*",
                                new Node("-1",null,null),
                                new Node("*",
                                        derivar(f.getDireita()),
                                        new Node("*",
                                                    new Node("cossec",null,f.getDireita()),
                                                    new Node("cotg",null,f.getDireita())
                                        )
                                )
                );
            case "cotg":
                return new Node("*",
                                new Node("-1",null,null),
                                new Node("*",
                                        derivar(f.getDireita()),
                                        new Node("^",
                                                    new Node("cossec",null,f.getDireita()),
                                                    new Node("2",null,null)
                                        )
                                )
                );
            case "ln":
                return new Node("/",derivar(f.getDireita()),f.getDireita());
            case "log":
                return new Node("/",
                                derivar(f.getDireita()),
                                new Node("*",
                                        f.getDireita(),
                                        new Node("ln",
                                            null,
                                            new Node("10",
                                            null,
                                            null
                                        )
                                    )
                                )
                            );
            case "x":
                return new Node("1",null,null);
            default:
                return new Node("0",null,null);
        }
    }
}