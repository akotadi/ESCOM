function Quote( t, a )
{	this.text = t;
	this.author = a;
};

var quotes = 
[ 
new Quote( 'You can use an eraser on the drafting table or a sledge hammer on the construction site.', 'Frank Lloyd Wright' ),
new Quote( 'All programmers are optimists.', 'Frederick P. Brooks, Jr' ),
new Quote( 'The structure of a system tends to mirror the structure of the group producing it.', 'Mel Conway' ),
new Quote( 'Many people tell the story of the CEO of a software company who claimed that his'
			+' product would be object oriented because it was written in C++.'
			+' Some tell the story without knowing that it is a joke.', 'Adele Goldberg' ),
new Quote('Superior technology breeds superior results.', 'Frink' ),
new Quote('Adding manpower to a late software project makes it later.', 'Fred Brooks' ),
new Quote('Excessive or irrational schedules are probably the single most destructive influence in all of software.', 'Capers Jones'),
new Quote('We can write good or bad programs with any tool.'
			+' Unless we teach people how to design, the languages matter very little.', 'David Parnas' ),
new Quote("If good software engineering were really all just common sense, we should "
		  +" expect to see projects routinely meet their schedule and budget targets"
		  +" and delight their end users.", "Steve McConnell" ),
new Quote("When you want a project in the worst way, that is often how you get it.", "Watts Humphrey"),
new Quote("For every complex problem, there is a simple answer, and it's wrong", "H.L. Mencken"),
new Quote("If you don't know where you are going, you will probably end up somewhere else.", "Laurence J. Peter"),
new Quote("A long habit of not thinking a thing wrong, gives it a superficial appearance of being right, and raises at first a formidable outcry in defence of custom.", "Thomas Paine")
];
var quote = quotes[  Math.floor(Math.random()*1000) % quotes.length ];
var s = '<p class="'+ (quote.text.length<150 ? 'shortquote' : 'longquote')+'">"' + quote.text + '"</p>';
if( quote.author.length > 0 )
	s += '<p class=quoteby> -' + quote.author + '</p>';
document.write( s );
