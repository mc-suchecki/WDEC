function [riskvals, results, parameters] = get_chart_points(volume)
% The function returns pars of risk values and results. This points are a 
% result of minimazation of risk at a given result value.
% The minimaztion is computed by fmincon method. This method uses nonlinear
% nonequality constrain "confuneq". x0 is a start point of searching. The
% price has a lower bound of 21.65. There is no risk of
% unsold products at this price in researched market. Its upper bound is 28.
% At this price there is a 100% risk of unsold selling product. 
% Possible result requirements range form 11.43*volume to 18.1*volume.
% This is mainly based on price boundaries.
% With required result 11.43 products are sold at 0% risk.
% With required result 18.1 products are sold at 100% risk.
 
    lower_result_bound = 11.43*volume;
    upper_result_bound = 18.1*volume;
    iteration_step = 0.5*volume;
    
    number_of_points =  int64((upper_result_bound - lower_result_bound)/iteration_step);
    %TOADD Cast number_of_points to integer to avoid worning
   
    results = zeros(number_of_points,1);
    riskvals = zeros(number_of_points,1);
    parameters = ones(number_of_points,5);
    
    x0 = [ 26,     70,     0,      0,      0]';  
    lb = [ 21.65,0,0,0,0];
    ub = [28, 100, Inf, Inf, Inf];
    options = optimset('Algorithm','active-set','Display','off');
    
    i = 1;
    for result = lower_result_bound : iteration_step : upper_result_bound 
        [par riskval] = fmincon(@minimize_risk,x0,[],[],[],[],lb,ub,...
            @(x)confuneq(x,volume,result),options);
        results(i) = result;
        riskvals(i) = riskval;
        parameters(i,:) = par;
        i = i +1;
    end
    figure, plot(riskvals,results,'*');
end
