package cn.hncu.beanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class MyBeanUtils1 {
	
	public static Object populate(Class cls ,Map map) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Object obj = null;
		
		//1�����෴��new������
		obj = cls.newInstance();
		
		//2 �����෴�����new�Ķ�����������ֵ(��������Java���ù淶)--��ͨ��setter��������
		//2.1���������и�������������
		Field flds[] = cls.getDeclaredFields();//getDeclaredFields()����Class�����е��ֶΣ�����˽���ֶΣ�
		for(Field fld:flds){
			//��ȡ��fld�����������������
			String fldName = fld.getName();
			//��������������map��ȥ��ȡ���ݣ�ֻ�����ݷǿղ���Ҫ������������ֵ 
			Object value = map.get(fldName);
			if(value==null){//���map�в����ڶ�Ӧ���������ݣ����������������ʾ��Ϣ
				System.out.println(fldName+"������Ϊ��");
			}else{
				//���map�д��ڶ�Ӧ���������ݣ������������ó�����setter����������
				String mothodName = "set"+fldName.substring(0, 1).toUpperCase()+fldName.substring(1);
				
				 //���ݷ������Ͳ�������������(��ʵ�������Ե�����)�����Method����
				Class paramTypes[] = new Class[1];
				paramTypes[0] = fld.getType();
				Method method = cls.getDeclaredMethod(mothodName, paramTypes);
				
				//���ø�method����������ķ���
				Object args[] = new Object[1];
				args[0]=value;
				method.invoke(obj, args);
			}
		}
		return obj;
	}
	
}
