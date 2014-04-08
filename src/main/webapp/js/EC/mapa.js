function toCanvas() {
    var elemento=null;
    $('.mapaFuncional').each(function(i) {
        elemento = $(this);
    });
    html2canvas(elemento, {
        onrendered: function(canvas) {
            var w = window.open('about:blank', 'Mapa Funcional');
            var data = canvas.toDataURL("image/png");
            w.document.write('<img src="' + data + '"/>');

        }
    });
    return false;
}
$(function() {
    addClass();
});