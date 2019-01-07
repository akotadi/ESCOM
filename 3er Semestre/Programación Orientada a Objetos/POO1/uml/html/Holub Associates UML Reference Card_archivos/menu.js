// CASCADING POPUP MENUS v5.1 by Angus Turnbull (compacted)
// More info at http://www.twinhelix.com 

var isDOM=document.getElementById?1:0;
var isIE=document.all?1:0;
var isNS4=navigator.appName=='Netscape'&&!isDOM?1:0;
var isIE4=isIE&&!isDOM?1:0;
var isOp=window.opera?1:0;
var isDyn=isDOM||isIE||isNS4;

function getRef(id, par)
{
par=!par?document:(par.navigator?par.document:par);
return (isIE ? par.all[id] :
(isDOM ? (par.getElementById?par:par.ownerDocument).getElementById(id) :
(isNS4 ? par.layers[id] : null)));
}

function getSty(id, par)
{
var r=getRef(id, par);
return r?(isNS4?r:r.style):null;
}

if (!window.LayerObj) var LayerObj = new Function('id', 'par',
'this.ref=getRef(id, par); this.sty=getSty(id, par); return this');
function getLyr(id, par) { return new LayerObj(id, par) }

function LyrFn(fn, fc)
{
LayerObj.prototype[fn] = new Function('var a=arguments,p=a[0],px=isNS4||isOp?0:"px"; ' +
'with (this) { '+fc+' }');
}
LyrFn('x','if (!isNaN(p)) sty.left=p+px; else return parseInt(sty.left)');
LyrFn('y','if (!isNaN(p)) sty.top=p+px; else return parseInt(sty.top)');
LyrFn('vis','sty.visibility=p');
LyrFn('bgColor','if (isNS4) sty.bgColor=p?p:null; ' +
'else sty.background=p?p:"transparent"');
LyrFn('bgImage','if (isNS4) sty.background.src=p?p:null; ' +
'else sty.background=p?"url("+p+")":"transparent"');
LyrFn('clip','if (isNS4) with(sty.clip){left=a[0];top=a[1];right=a[2];bottom=a[3]} ' +
'else sty.clip="rect("+a[1]+"px "+a[2]+"px "+a[3]+"px "+a[0]+"px)" ');
LyrFn('write','if (isNS4) with (ref.document){write(p);close()} else ref.innerHTML=p');
LyrFn('alpha','var f=ref.filters,d=(p==null); if (f) {' +
'if (!d&&sty.filter.indexOf("alpha")==-1) sty.filter+=" alpha(opacity="+p+")"; ' +
'else if (f.length&&f.alpha) with(f.alpha){if(d)enabled=false;else{opacity=p;enabled=true}} }' +
'else if (isDOM) sty.MozOpacity=d?"":p+"%"');

function setLyr(lVis, docW, par)
{
if (!setLyr.seq) setLyr.seq=0;
if (!docW) docW=0;
var obj = (!par ? (isNS4 ? window : document.body) :
(!isNS4 && par.navigator ? par.document.body : par));
var IA='insertAdjacentHTML', AC='appendChild', newID='_js_layer_'+setLyr.seq++;

if (obj[IA]) obj[IA]('beforeEnd', '<div id="'+newID+'" style="position:absolute"></div>');
else if (obj[AC])
{
var newL=document.createElement('div');
obj[AC](newL); newL.id=newID; newL.style.position='absolute';
}
else if (isNS4)
{
var newL=new Layer(docW, obj);
newID=newL.id;
}

var lObj=getLyr(newID, par);
with (lObj) if (ref) { vis(lVis); x(0); y(0); sty.width=docW+(isNS4?0:'px') }
return lObj;
}

var CSSmode=document.compatMode;
CSSmode=(CSSmode&&CSSmode.indexOf('CSS')!=-1)||isDOM&&!isIE||isOp?1:0;

if (!window.page) var page = { win: window, minW: 0, minH: 0, MS: isIE&&!isOp,
db: CSSmode?'documentElement':'body' }

page.winW=function()
{ with (this) return Math.max(minW, MS?win.document[db].clientWidth:win.innerWidth) }
page.winH=function()
{ with (this) return Math.max(minH, MS?win.document[db].clientHeight:win.innerHeight) }

page.scrollX=function()
{ with (this) return MS?win.document[db].scrollLeft:win.pageXOffset }
page.scrollY=function()
{ with (this) return MS?win.document[db].scrollTop:win.pageYOffset }

function popOver(mN, iN) { with (this)
{
clearTimeout(hideTimer);
overM = mN;
overI = iN;
if (iN && this.onmouseover) onmouseover(mN, iN);

litOld = litNow;
litNow = new Array();
var litM = mN, litI = iN;
while(1)
{
litNow[litM] = litI;
if (litM == 'root') break;
litI = menu[litM][0].parentItem;
litM = menu[litM][0].parentMenu;
}

var same = true;
for (var z in menu) if (litNow[z] != litOld[z]) same = false;
if (same) return;

clearTimeout(showTimer);

for (thisM in menu) with (menu[thisM][0])
{
if (!lyr) continue;

litI = litNow[thisM];
oldI = litOld[thisM];

if (litI && (litI != oldI)) changeCol(thisM, litI, true);

if (oldI && (oldI != litI)) changeCol(thisM, oldI, false);

if (litI && !visNow && (thisM != 'root'))
{
showMenu(thisM);
visNow = true;
}

if (isNaN(litI) && visNow)
{
hideMenu(thisM);
visNow = false;
}
}

nextMenu = '';
if ((menu[mN][iN].type == 'sm:') && !menu[mN][0].subsOnClick)
{
var targ = menu[mN][iN].href, lyrM = menu[mN][0].lyr;

var showStr = 'with ('+myName+') { menu.'+targ+'[0].visNow = true; ' +
'position("'+targ+'"); showMenu("'+targ+'") }';
nextMenu = targ;
if (showDelay) showTimer = setTimeout(showStr, showDelay);
else eval(showStr);
}
}}

function popOut(mN, iN) { with (this)
{
if ((mN != overM) || (iN != overI)) return;

if (this.onmouseout) onmouseout(mN, iN);

var thisI = menu[mN][iN];

if (thisI.href != nextMenu)
{
clearTimeout(showTimer);
nextMenu = '';
}

if (hideDelay)
{
var delay = ((mN == 'root') && (thisI.type != 'sm:')) ? 50 : hideDelay;
hideTimer = setTimeout(myName + '.over("root", 0)', delay);
}

overM = 'root';
overI = 0;
}}

function popClick(mN, iN) { with (this)
{
if (this.onclick) onclick(mN, iN);

var thisI = menu[mN][iN], hideM = true;

with (thisI) switch (type)
{
case 'sm:':
{
if (menu[overM][0].subsOnClick)
{
menu[href][0].visNow = true;
position(href);
showMenu(href);
hideM = false;
}
break;
}
case 'js:': { eval(href); break }
case '': type = 'window';
default: if (href) eval(type + '.location.href = "' + href + '"');
}

if (hideM) over('root', 0);
}}

function popChangeCol(mN, iN, isOver) { with (this.menu[mN][iN])
{
if (!lyr || !lyr.ref) return;

var col = isOver?overCol:outCol;
var bgFn = (col.indexOf('.')==-1) ? 'bgColor' : 'bgImage';
if (isNS4) lyr[bgFn](col);

if ((overClass != outClass) || (outBorder != overBorder)) with (lyr)
{
if (isNS4) write(this.getHTML(mN, iN, isOver));
else
{
ref.className = (isOver ? overBorder : outBorder);
var chl = (isDOM ? ref.childNodes : ref.children)
if (chl) for (var i = 0; i < chl.length; i++) chl[i].className = isOver?overClass:outClass;
}
}

if (!isNS4) lyr[bgFn](col);

if (outAlpha != overAlpha) lyr.alpha(isOver ? overAlpha : outAlpha);
}}

function popPosition(posMN) { with (this)
{
for (mN in menu)
{
if (posMN && (posMN != mN)) continue;
with (menu[mN][0])
{
if (!lyr || !lyr.ref || !visNow) continue;

var pM, pI, newX = eval(offX), newY = eval(offY);
if (mN != 'root')
{
pM = menu[parentMenu];
pI = pM[parentItem].lyr;
if (!pI) continue;
}

var eP = eval(par);
var pW = (eP && eP.navigator ? eP : window);

with (pW.page) var sX=scrollX(), wX=sX+winW(), sY=scrollY(), wY=winH()+sY;
wX = isNaN(wX)||!wX ? 9999 : wX;
wY = isNaN(wY)||!wY ? 9999 : wY;

if (pM && typeof(offX)=='number') newX = Math.max(sX,
Math.min(newX+pM[0].lyr.x()+pI.x(), wX-menuW-(isIE?5:20)));
if (pM && typeof(offY)=='number') newY = Math.max(sY,
Math.min(newY+pM[0].lyr.y()+pI.y(), wY-menuH-(isIE?5:20)));

lyr.x(newX);
lyr.y(newY);
}
}
}}

function addProps(obj, data, names, addNull)
{
for (var i = 0; i < names.length; i++) if(i < data.length || addNull) obj[names[i]] = data[i];
}

function ItemStyle()
{
var names = ['len', 'spacing', 'popInd', 'popPos', 'pad', 'outCol', 'overCol', 'outClass',
'overClass', 'outBorder', 'overBorder', 'outAlpha', 'overAlpha', 'normCursor', 'nullCursor'];
addProps(this, arguments, names, true);
}

function popStartMenu(mName) { with (this)
{
if (!menu[mName]) { menu[mName] = new Array(); menu[mName][0] = new Object(); }

actMenu = menu[mName];
aM = actMenu[0];
actMenu.length = 1;

var names = ['isVert', 'isVert', 'offX','offY', 'width', 'itemSty', 'par',
'parentMenu', 'parentItem', 'visNow', 'oncreate', 'subsOnClick'];
addProps(aM, arguments, names, true);

aM.extraHTML = '';
aM.menuW = aM.menuH = 0;

if (!aM.lyr) aM.lyr = null;

if (mName == 'root') menu.root[0].oncreate = new Function('this.visNow=true; ' +
myName + '.position("root"); this.lyr.vis("visible")');
}}

function popAddItem() { with (this) with (actMenu[0])
{
var aI = actMenu[actMenu.length] = new Object();

var names = ['text', 'href', 'type', 'itemSty', 'len', 'spacing', 'popInd', 'popPos',
'pad', 'outCol', 'overCol', 'outClass', 'overClass', 'outBorder', 'overBorder',
'outAlpha', 'overAlpha', 'normCursor', 'nullCursor',
'iX', 'iY', 'iW', 'iH', 'lyr'];
addProps(aI, arguments, names, true);

var iSty = (arguments[3] ? arguments[3] : actMenu[0].itemSty);
for (prop in iSty) if (aI[prop]+'' == 'undefined') aI[prop] = iSty[prop];

if (aI.outBorder)
{
if (isNS4) aI.pad++;
}

aI.iW = (isVert ? width : aI.len);
aI.iH = (isVert ? aI.len : width);

var lastGap = (actMenu.length > 2) ? actMenu[actMenu.length - 2].spacing : 0;

var spc = ((actMenu.length > 2) && aI.outBorder ? 1 : 0);

if (isVert)
{
menuH += lastGap - spc;
aI.iX = 0; aI.iY = menuH;
menuW = width; menuH += aI.iH;
}
else
{
menuW += lastGap - spc;
aI.iX = menuW; aI.iY = 0;
menuW += aI.iW; menuH = width;
}

if (aI.outBorder && CSSmode)
{
aI.iW -= 2;
aI.iH -= 2;
}
}}

function popGetHTML(mN, iN, isOver) { with (this)
{
var itemStr = '';
with (menu[mN][iN])
{
var textClass = (isOver ? overClass : outClass);

if ((type == 'sm:') && popInd)
{
if (isNS4) itemStr += '<layer class="' + textClass + '" left="'+ ((popPos+iW)%iW) +
'" top="' + pad + '" height="' + (iH-2*pad) + '">' + popInd + '</layer>';
else itemStr += '<div class="' + textClass + '" style="position: absolute; left: ' +
((popPos+iW)%iW) + 'px; top: ' + pad + 'px; height: ' + (iH-2*pad) + 'px">' + popInd + '</div>';
}

if (isNS4) itemStr += (outBorder ? '<span class="' + (isOver?overBorder:outBorder) +
'"><spacer type="block" width="' + (iW-8) + '" height="' + (iH-8) + '"></span>' : '') +
'<layer left="' + pad + '" top="' + pad + '" width="' + (iW-2*pad) + '" height="' +
(iH-2*pad) + '"><a class="' + textClass + '" href="#" ' +
'onClick="return false" onMouseOver="status=\'\'; ' + myName + '.over(\'' + mN + '\',' +
iN + '); return true">' + text + '</a></layer>';

else itemStr += '<div class="' + textClass + '" style="position: absolute; left: ' + pad +
'px; top: ' + pad + 'px; width: ' + (iW-2*pad) + 'px; height: ' + (iH-2*pad) + 'px">' +
text + '</div>';
}
return itemStr;
}}

function popUpdate(docWrite, upMN) { with (this)
{
if (!isDyn) return;

for (mN in menu) with (menu[mN][0])
{
if (upMN && (upMN != mN)) continue;

var str = '';

for (var iN = 1; iN < menu[mN].length; iN++) with (menu[mN][iN])
{
var itemID = myName + '_' + mN + '_' + iN;

var targM = menu[href];
if (targM && (type == 'sm:'))
{
targM[0].parentMenu = mN;
targM[0].parentItem = iN;
}

var isImg = (outCol.indexOf('.') != -1) ? true : false;

if (!isIE && normCursor=='hand') normCursor = 'pointer';

if (isDOM || isIE4)
{
str += '<div id="' + itemID + '" ' + (outBorder ? 'class="'+outBorder+'" ' : '') +
'style="position: absolute; left: ' + iX + 'px; top: ' + iY + 'px; width: ' + iW +
'px; height: ' + iH + 'px; z-index: 1000; background: ' + (isImg?'url('+outCol+')':outCol) +
((typeof(outAlpha)=='number') ? '; filter: alpha(opacity='+ outAlpha + '); -moz-opacity: ' +
(outAlpha/100) : '') +
'; cursor: ' + ((type!='sm:' && href) ? normCursor : nullCursor) + '" ';
}
else if (isNS4)
{
str += '<layer id="' + itemID + '" left="' + iX + '" top="' + iY + '" width="' +
iW + '" height="' + iH + '" z-index="1000" ' +
(outCol ? (isImg ? 'background="' : 'bgcolor="') + outCol + '" ' : '');
}

var evtMN = '(\'' + mN + '\',' + iN + ')"';
str += 'onMouseOver="' + myName + '.over' + evtMN +
' onMouseOut="' + myName + '.out' + evtMN +
' onClick="' + myName + '.click' + evtMN + '>' +
getHTML(mN, iN, false) + (isNS4 ? '</layer>' : '</div>');

}

var eP = eval(par);

setTimeout(myName + '.setupRef(' + docWrite + ', "' + mN + '")', 50);

var mVis = visNow ? 'visible' : 'hidden';

if (docWrite)
{
var targFr = (eP && eP.navigator ? eP : window);
targFr.document.write('<div id="' + myName + '_' + mN + '_Div" style="position: absolute; ' +
'visibility: ' + mVis + '; left: 0px; top: 0px; width: ' + (menuW+2) + 'px; height: ' +
(menuH+2) + 'px; z-index: 1000">' + str + extraHTML + '</div>');
}
else
{
if (!lyr || !lyr.ref) lyr = setLyr(mVis, menuW, eP);
else if (isIE4) setTimeout(myName + '.menu.' + mN + '[0].lyr.sty.width=' + (menuW+2), 50);

with (lyr) { sty.zIndex = 1000; write(str + extraHTML) }
}

}
}}

function popSetupRef(docWrite, mN) { with (this) with (menu[mN][0])
{
if (docWrite || !lyr || !lyr.ref) lyr = getLyr(myName + '_' + mN + '_Div', eval(par));

for (var i = 1; i < menu[mN].length; i++)
menu[mN][i].lyr = getLyr(myName + '_' + mN + '_' + i, (isNS4?lyr.ref:eval(par)));

if (menu[mN][0].oncreate) oncreate();
}}

function PopupMenu(myName)
{
this.myName = myName;

this.showTimer = 0;
this.hideTimer = 0;
this.showDelay = 0;
this.hideDelay = 500;
this.showMenu = '';

this.menu =  new Array();
this.litNow = new Array();
this.litOld = new Array();

this.overM = 'root';
this.overI = 0;

this.actMenu = null;

this.over = popOver;
this.out = popOut;
this.changeCol = popChangeCol;
this.position = popPosition;
this.click = popClick;
this.startMenu = popStartMenu;
this.addItem = popAddItem;
this.getHTML = popGetHTML;
this.update = popUpdate;
this.setupRef = popSetupRef;

this.showMenu = new Function('mName', 'this.menu[mName][0].lyr.vis("visible")');
this.hideMenu = new Function('mName', 'this.menu[mName][0].lyr.vis("hidden")');
}

function menuAnim(menuObj, menuName, dir)
{
var mD = menuObj.menu[menuName][0];
if (!mD.timer) mD.timer = 0;
if (!mD.counter) mD.counter = 0;

with (mD)
{
clearTimeout(timer);

if (!lyr || !lyr.ref) return;
if (dir>0) lyr.vis('visible');
lyr.sty.zIndex = 1001 + dir;

lyr.clip(0, 0, menuW+2, (menuH+2)*Math.pow(Math.sin(Math.PI*counter/200),0.75) );
if ((isDOM&&!isIE) && (counter>=100)) lyr.sty.clip='';

counter += dir;
if (counter>100) counter = 100;
else if (counter<0) { counter = 0; lyr.vis('hidden') }
else timer = setTimeout(menuObj.myName+'.'+(dir>0?'show':'hide')+'Menu("'+menuName+'")', 40);
}
}

function menuFilterShow(menuObj, menuName, filterName)
{
var mD = menuObj.menu[menuName][0];
with (mD.lyr)
{
sty.filter = filterName;
var f = ref.filters;
if (f&&f.length&&f[0]) f[0].Apply();
vis('visible');
if (f&&f.length&&f[0]) f[0].Play();
}
}

function addMenuBorder(mObj, iS, alpha, bordCol, bordW, backCol, backW)
{
for (var mN in mObj.menu)
{
var mR=mObj.menu[mN], dS='<div style="position:absolute; background:';
if (mR[0].itemSty != iS) continue;
for (var mI=1; mI<mR.length; mI++)
{
mR[mI].iX += bordW+backW;
mR[mI].iY += bordW+backW;
}
mW = mR[0].menuW += 2*(bordW+backW);
mH = mR[0].menuH += 2*(bordW+backW);

if (isNS4) mR[0].extraHTML += '<layer bgcolor="'+bordCol+'" left="0" top="0" width="'+mW+
'" height="'+mH+'" z-index="980"><layer bgcolor="'+backCol+'" left="'+bordW+'" top="'+
bordW+'" width="'+(mW-2*bordW)+'" height="'+(mH-2*bordW)+'" z-index="990"></layer></layer>';
else mR[0].extraHTML += dS+bordCol+'; left:0px; top:0px; width:'+mW+'px; height:'+mH+
'px; z-index:980; '+(alpha!=null?'filter:alpha(opacity='+alpha+'); -moz-opacity:'+(alpha/100):'')+
'">'+dS+backCol+'; left:'+bordW+'px; top:'+bordW+'px; width:'+(mW-2*bordW)+'px; height:'+
(mH-2*bordW)+'px; z-index:990"></div></div>';
}
}

function addDropShadow(mObj, iS)
{
for (var mN in mObj.menu)
{
var a=arguments, mD=mObj.menu[mN][0], addW=addH=0;
if (mD.itemSty != iS) continue;
for (var shad=2; shad<a.length; shad++)
{
var s = a[shad];
if (isNS4) mD.extraHTML += '<layer bgcolor="'+s[1]+'" left="'+s[2]+'" top="'+s[3]+'" width="'+
(mD.menuW+s[4])+'" height="'+(mD.menuH+s[5])+'" z-index="'+(arguments.length-shad)+'"></layer>';
else mD.extraHTML += '<div style="position:absolute; background:'+s[1]+'; left:'+s[2]+
'px; top:'+s[3]+'px; width:'+(mD.menuW+s[4])+'px; height:'+(mD.menuH+s[5])+'px; z-index:'+
(a.length-shad)+'; '+(s[0]!=null?'filter:alpha(opacity='+s[0]+'); -moz-opacity:'+(s[0]/100):'')+
'"></div>';
addW=Math.max(addW, s[2]+s[4]);
addH=Math.max(addH, s[3]+s[5]);
}
mD.menuW+=addW; mD.menuH+=addH;
}
}
