// Menu-related stuff:
//
var rArrow = '<img src="/images/right.arrow.black.gif">' ;
var width 		  = 90; // Main-menu width

// hBar  = horizontal main menu bar
// subM  = submenu
// subM2 = second-level submenu

var hBar = new ItemStyle(width, 0, '',       0, 0,'#cc0000','#aa0000','highText','highText', '', 		   '' 		  ,null,null,'hand','default');

var subM = new ItemStyle(   22, 0, rArrow, -15, 3,'#ffcc66','#ff9900','lowText' ,'highText', 'itemBorder','itemBorder',null,null,'hand','default');

var subM2= new ItemStyle(   22, 0, rArrow, -15, 3,'#ffee88','#ff9900','lowText' ,'highText', 'itemBorder','itemBorder',null,null,'hand','default');

// 'subBlank' is similar, but has an 'off' border the same colour as its background so it
// appears borderless when dim, and 1px spacing between items to show the hover border.

var subBlank = new ItemStyle(22, 1, '&gt;', -15, 3, '#CCCCDD', '#6699CC', 'lowText', 'highText', 'itemBorderBlank', 'itemBorder', null, null, 'hand', 'default');

var pMenu = new PopupMenu('pMenu');
with (pMenu)
{
	if( isIE )
		startMenu('root', false, 153, 112, 17, hBar);
	else
		startMenu('root', false, 151, 105, 17, hBar);

	addItem('<div class="m"> Services 	  </div>'	, 'mServices',		'sm:', hBar, 63);
//	addItem('<div class="m"> Training	  </div>'	, 'mTraining',		'sm:', hBar, 62);
	addItem('<div class="m"> Publications </div>'	, 'mPublications',	'sm:', hBar, 87);
	addItem('<div class="m"> Goodies	  </div>'	, 'mGoodies',		'sm:', hBar, 61);
	addItem('<div class="m"> Newsletter	  </div>'	, 'mNewsletter',	'sm:', hBar, 79);
	addItem('<div class="m"> Company 	  </div>'	, 'mCompany', 		'sm:', hBar, 87);

	addItem('<div class="m"> <em>Contact</em>  </div>', '/company/contact.html' , '', hBar, 54);
	addItem('<div class="m"> <em>Search</em>   </div>', '/search.html',			'', hBar, 50);
	addItem('<div class="m"> <em>Home</em>	   </div>', '/index.html',			'', hBar, 50);

	startMenu('mServices', true, 0, 22, 320, subM);
	addItem('Training (OO Design, Agile Process, Java, C++)', '/training/index.html', '');
	addItem('Design Review', '/services/design_review.html', '');
	addItem('Mentoring', '/services/mentoring.html', '');
	addItem('Hiring', '/services/hiring.html', '');
	addItem('Expert Witness & Due-Diligence', '/expert/index.html', '');

//	startMenu('mTraining', true, 0, 22, 240, subM);
//	addItem('Object-Oriented Design, UML, Java', '/training/index.html', '');

	startMenu('mPublications', true, 0, 22, 210, subM);
	addItem('Holub on Patterns',		  		'/goodies/patterns/index.html', '');
	addItem('Published Articles', 				'/publications/articles/index.html',	'');
	addItem('Other Works by Allen Holub',		'/publications/other/index.html', '');
	addItem('Course Notes &amp; Other Slides',	'/publications/notes_and_slides/index.html', '');
	addItem('Newsletter Archive',   			'/newsletter/archive/index.html', '');

	startMenu('mGoodies', true, 0, 22, 175 , subM);
	addItem('Software',				'/software/index.html',	 	'');

	addItem('&nbsp;&nbsp;&nbsp;Holub on Patterns',		  			'/goodies/patterns/index.html', '');
	addItem('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Embedded SQL',		'/software/holubSQL/index.html', '');
	addItem('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The Game of Life',	'/software/life/index.html', '');

	addItem('&nbsp;&nbsp;&nbsp;Compiler Design in C', '/software/compiler.design.in.c.html', '');
	addItem('&nbsp;&nbsp;&nbsp;Taming Java Threads' , '/software/taming.java.threads.html', '');
	addItem('&nbsp;&nbsp;&nbsp;Articles' , 			'/software/index.html#articles', '');

	addItem('Goodies',				'/goodies/index.html',	 	'');
	addItem('&nbsp;&nbsp;&nbsp;UML Quick Reference','/goodies/uml/index.html',	'');
	addItem('&nbsp;&nbsp;&nbsp;An OO Reading List','/goodies/books.html',	''	  );
	addItem('&nbsp;&nbsp;&nbsp;Other OO-Design/UML','/goodies/index.html#OOD',	'');
	addItem('&nbsp;&nbsp;&nbsp;Java',				'/goodies/index.html#java', '');
	addItem('&nbsp;&nbsp;&nbsp;Miscellaneous',		'/goodies/index.html#misc', '');

	startMenu('mCompany', true, -20, 22, 130 , subM);
	addItem('Contact Us',  	 	 '/company/contact.html', '');
	addItem('Testimonials',  	 '/company/testimonials.html', '');
	addItem('Past Clients',  	 '/company/clients.html', '' );
	addItem('About Allen Holub', '/company/allen_holub.html', '');
	addItem('Allen\'s Schedule', '/company/schedule.html', '');
	addItem('Privacy Policy', 	 '/company/privacy.html', '');

	startMenu('mNewsletter', true, 0, 22, 120, subM);
	addItem('Subscribe', 		'/newsletter/index.html', '');
	addItem('Archive',   		'/newsletter/archive/index.html', '');
	addItem('Subscribers Only', '/subscribers/index.html', '');
}

addMenuBorder(pMenu, window.subBlank, null, '#666666', 1, '#CCCCDD', 2);
addDropShadow(pMenu, window.subM, 	  [40,"#333333",6,6,-4,-4], [40,"#666666",4,4,0,0]);
addDropShadow(pMenu, window.subBlank, [40,"#333333",6,6,-4,-4], [40,"#666666",4,4,0,0]);

if (!isOp && navigator.userAgent.indexOf('rv:0.')==-1)
{
 pMenu.showMenu = new Function('mN','menuAnim(this, mN, 10)');
 pMenu.hideMenu = new Function('mN','menuAnim(this, mN, -10)');
}
if (!isNS4)
{ pMenu.update(true);
}
else
{
var popOldOL = window.onload;
window.onload = function()
{
if (popOldOL) popOldOL();
pMenu.update();
}
}
var nsWinW = window.innerWidth, nsWinH = window.innerHeight, popOldOR = window.onresize;
window.onresize = function()
{
if (popOldOR) popOldOR();
if (isNS4 && (nsWinW!=innerWidth || nsWinH!=innerHeight)) history.go(0);
pMenu.position();
}
window.onscroll = function()
{
pMenu.position();
}
if (isNS4)
{
document.captureEvents(Event.CLICK);
document.onclick = function(evt)
{
with (pMenu) if (overI) click(overM, overI);
return document.routeEvent(evt);
}
}
if (!isIE || isOp)
{
var nsPX=pageXOffset, nsPY=pageYOffset;
setInterval('if (nsPX!=pageXOffset || nsPY!=pageYOffset) ' + '{ nsPX=pageXOffset; nsPY=pageYOffset; window.onscroll() }', 50);
}
