let pointId = 0;
const points = new Map();
let graph_click_enabled = false;
const enable_graph_button = document.getElementById('enable-graph');
const elt = document.getElementById('graph');
const calculator = Desmos.GraphingCalculator(elt, {
    keypad: false,
    expressions: false,
    settingsMenu: false,
    invertedColors: true,
    xAxisLabel: 'x',
    yAxisLabel: 'y',
    xAxisStep: 1,
    yAxisStep: 1,
    xAxisArrowMode: Desmos.AxisArrowModes.POSITIVE,
    yAxisArrowMode: Desmos.AxisArrowModes.POSITIVE
});

calculator.setMathBounds({
    left: -5,
    right: 5,
    bottom: -5,
    top: 5
});

let newDefaultState = calculator.getState();
calculator.setDefaultState(newDefaultState);

function drawGraphByR(r) {
    for (let i = 0; i < pointId; i++) {
        calculator.removeExpression({id: 'point' + i});
    }
    points.forEach((v,k) => {
        drawPointXYRResID(v.x, v.y, v.r, v.result, k);
    });
    calculator.setExpression({
        id: '1',
        latex: 'x\\ <=\\ r\\ \\left\\{y\\ge0\\right\\}\\left\\{y\\le(r/2)\\right\\} \\left\\{x>=0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '2',
        latex: 'x^{2}+y^{2}\\le (r/2)^{2}\\ \\left\\{x<=0\\right\\} \\left\\{y>=0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '3',
        latex: 'y\\ =\\ r/2\\ \\left\\{x\\ge0\\right\\}\\left\\{x\\le(r)\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '4',
        latex: '-x-y\\le r\\ \\left\\{x\\le0\\right\\}\\left\\{y\\le0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({id: '8', latex: 'r=' + r, lineOpacity: 0});
}

/*function drawPoint(x, y, r) {
    drawGraphByR(r);
    drawPointXY(x, y);
}

function drawPointXY(x, y) {
    calculator.setExpression({
        id: 'point' + pointId++,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.RED
    });
}*/

function drawPointXYRRes(x, y, r, result) {
    if (r === undefined)
        r = 0;
    points.set('point' + pointId, {x, y, r, result})
    calculator.setExpression({
        id: 'point' + pointId++,
        latex: '(' + x + ', ' + y + ')',
        color: result ? Desmos.Colors.PURPLE : Desmos.Colors.BLUE
    });
}

function drawPointXYRResID(x, y, r, result, point_id) {
    const actualR = Number(getCurrentR());
    if (+actualR === +r) {
        calculator.setExpression({
            id: point_id,
            latex: '(' + x + ', ' + y + ')',
            color: result ? Desmos.Colors.PURPLE : Desmos.Colors.BLUE
        });
    }
}

function inRectangle(point, rect) {
    return (
        point.x >= rect.left &&
        point.x <= rect.right &&
        point.y <= rect.top &&
        point.y >= rect.bottom
    )
}

function enable_graph() {
    if (graph_click_enabled) {
        elt.removeEventListener('click', handleGraphClick);
        graph_click_enabled = false;
        enable_graph_button.textContent = "Enable graph aiming";
    } else {
        elt.addEventListener('click', handleGraphClick);
        graph_click_enabled = true;
        enable_graph_button.textContent = "Disable graph aiming";
    }
}

function handleGraphClick (evt) {

    const rect = elt.getBoundingClientRect();
    const x = evt.clientX - rect.left;
    const y = evt.clientY - rect.top;
    // Note, pixelsToMath expects x and y to be referenced to the top left of
    // the calculator's parent container.
    const mathCoordinates = calculator.pixelsToMath({x: x, y: y});

    if (!inRectangle(mathCoordinates, calculator.graphpaperBounds.mathCoordinates)) return;

    document.getElementById("graphSelect:graph-x").value = mathCoordinates.x;
    document.getElementById("graphSelect:graph-y").value = mathCoordinates.y;

    updateBeanValues();
}