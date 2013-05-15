function [c, ceq] = confuneq(x,volume,result)
% The function is used by fmincon method in get_chart_points function.
% Nonlinear inequality constraints c<=0
    c = -x(1)*volume+volume*hardcoded_compute_unit_cost(x(2)) +x(3) + x(4) + x(5) + result;
    ceq = 0; % dump value 0=0
end
