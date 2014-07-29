$(document).ready(function(){

    // HIDE ELEMENTS ON PAGE LOAD
    //$("#signature_container").hide();
    //$("#map_container").hide();
    $("#content").hide();

    // JSIG CALLBACK
    (function(a){var b={};a.publish=function(c,d){if(b[c]){var e=b[c],d=d||{};
        for(var f=0,g=e.length;f<g;f++){e[f].call(a,d)}}};a.subscribe=function(a,c)
    {if(!b[a]){b[a]=[]}b[a].push(c);return{topic:a,callback:c}};
        a.unsubscribe=function(a){var c=a.topic;if(b[c]){var d=b[c];
            for(var e=0,f=d.length;e<f;e++){if(d[e]===a.callback){d.splice(e,1)}}}}})(jQuery);

    // JSIG SIGNATURE INITS
    var width = $(window).width() *.99
    var height = $(window).height()*.9
    var $sigdiv = $("#newDiv").jSignature({width:width,height:height});

    $('#accept_button').click(function(){
        var sig_data = $sigdiv.jSignature('getData', 'default');
        $('input[id="signature"]').val(sig_data);
        //$("#signature_container").hide();
       // $("#details_container").show();
        $("#complete_container").show();
    });

    $("#clear_button").click(function(){
        $sigdiv.jSignature('reset');
        $('#id_signature_bin').val('');
        $("#complete_container").hide();
    });

    //if (Modernizr.touch){$('#scrollgrabber').height($('#content').height());}
});
